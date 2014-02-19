package chinesechecker.client;
import java.util.Collection;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import chinesechecker.test.GameApi.Delete;
import chinesechecker.test.GameApi.EndGame;
import chinesechecker.test.GameApi.Operation;
import chinesechecker.test.GameApi.Set;
import chinesechecker.test.GameApi.SetTurn;
import chinesechecker.test.GameApi.SetVisibility;
import chinesechecker.test.GameApi.Shuffle;
import chinesechecker.test.GameApi.VerifyMove;
import chinesechecker.test.GameApi.VerifyMoveDone;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

public class ChineseCheckersLogic {
	private static final String R = "Red"; // Red hand
	private static final String B = "Blue"; // Blue hand
	private static final String P = "Player"; // Player key (P0..P1)
	private static final String C = "Chessboard"; // Chessboard
	private static final String S = "State"; // State
	State state = null;	
	
	public VerifyMoveDone verify(VerifyMove verifyMove) {
		try {
			checkMoveIsLegal(verifyMove);
			return new VerifyMoveDone();
		} catch (Exception e) {
			return new VerifyMoveDone(verifyMove.getLastMovePlayerId(), e.getMessage());
		}
	}
	
	public void checkMoveIsLegal(VerifyMove verifyMove) {
		List<Operation> expectedOperations = getExpectedOperations(verifyMove);
		List<Operation> lastMove = verifyMove.getLastMove();		
		check(expectedOperations.toString().equals(lastMove.toString()), expectedOperations, lastMove);
		if (verifyMove.getLastState().isEmpty()) {
			check(verifyMove.getLastMovePlayerId() == verifyMove.getPlayerIds().get(0));
		}
	}

	List<Operation> getMove(State state) {
		List<Operation> operations = Lists.newArrayList();
		operations.add(new SetTurn(state.currentPlayIndex));
		operations.add(new Set(S, state));
		return operations;
	}

	List<Operation> getExpectedOperations(VerifyMove verifyMove) {
		List<Operation> lastMove = verifyMove.getLastMove();
		Map<String, Object> lastApiState = verifyMove.getLastState();
		List<Integer> playerIds = verifyMove.getPlayerIds();
		if (lastApiState.isEmpty()) {
			return getMoveInitial(verifyMove.checkerstate);
		}
		int lastMovePlayerId = verifyMove.getLastMovePlayerId();
		State lastState = gameApiStateToCheatState(lastApiState, verifyMove.checkerstate);
		return getMove(lastState);		
	}

	public List<Operation> getMoveInitial(State state) {
		List<Operation> operations = Lists.newArrayList();
		operations.add(new SetTurn(state.currentPlayIndex));
		for (int i = 0; i < state.getPlayCount(); i++) {
			operations.add(new Set(P + i, state.players[i].toString()));
		}
		operations.add(new Set(C, state.getChessBoard().toString()));
		return operations;
	}
	
	State gameApiStateToCheatState(Map<String, Object> gameApiState, State state) {
		return state;
	}

	public void check(boolean val, Object... debugArguments) {
		if (!val) {
			throw new RuntimeException("We have a hacker! debugArguments=\n" + Arrays.toString(debugArguments));
		}
	}
}