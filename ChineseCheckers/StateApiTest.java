package hw1test;

import static com.google.common.base.Preconditions.checkArgument;
import static hw1.Gamer.B;
import static hw1.Gamer.I;
import static hw1.Gamer.W;
import static hw1.Gamer._;
import static org.junit.Assert.assertEquals;
import hw1.Gamer;
import hw1.State;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

import static org.junit.Assert.*;

import org.junit.Test;

@RunWith(JUnit4.class)
public class StateApiTest {
	State state = new State();
	private void assertMoveOk(VerifyMove verifyMove) {
		state.checkMoveIsLegal(verifyMove);
	}
	private void assertHacker(VerifyMove verifyMove) {
		VerifyMoveDone verifyDone = state.verify(verifyMove);
	    assertEquals(verifyMove.getLastMovePlayerId(), verifyDone.getHackerPlayerId());
	}

	private final int bId = 1;
	private final int wId = 2;
	private final String playerId = "playerId";
	private final String turn = "turn"; // turn of which player (either W or B)
	private static final String WHITE = "W"; // White hand
	private static final String BLACK = "B"; // Black hand
	private final String board = "board"; // situation of the board
	private final List<Integer> visibleToW = ImmutableList.of(wId);
	private final List<Integer> visibleToB = ImmutableList.of(bId);
	private final Map<String, Object> wInfo = ImmutableMap.<String, Object>of(playerId, wId);
	private final Map<String, Object> bInfo = ImmutableMap.<String, Object>of(playerId, bId);
	private final List<Map<String, Object>> playersInfo = ImmutableList.of(wInfo, bInfo);
	private final Map<String, Object> emptyState = ImmutableMap.<String, Object>of();
	private final Map<String, Object> nonEmptyState = ImmutableMap.<String, Object>of("k", "v");

	private VerifyMove move(int lastMovePlayerId, Map<String, Object> lastState, List<Operation> lastMove) {
		return new VerifyMove(wId, playersInfo, emptyState, lastState, lastMove, lastMovePlayerId);
	}
	
	public static final Gamer[][] INIT_BOARD = {
		// 0  1  2  3  4  5  6  7  8  9  10 11 12 13 14 15 16 
		{ I, I, I, I, B, I, I, I, I, I, I, I, I, I, I, I, I }, // 0
		{ I, I, I, I, B, B, I, I, I, I, I, I, I, I, I, I, I }, // 1
		{ I, I, I, I, B, B, B, I, I, I, I, I, I, I, I, I, I }, // 2
		{ I, I, I, I, B, B, B, B, I, I, I, I, I, I, I, I, I }, // 3
		{ _, _, _, _, _, _, _, _, _, _, _, _, _, I, I, I, I }, // 4
		{ I, _, _, _, _, _, _, _, _, _, _, _, _, I, I, I, I }, // 5
		{ I, I, _, _, _, _, _, _, _, _, _, _, _, I, I, I, I }, // 6
		{ I, I, I, _, _, _, _, _, _, _, _, _, _, I, I, I, I }, // 7
		{ I, I, I, I, _, _, _, _, _, _, _, _, _, I, I, I, I }, // 8
		{ I, I, I, I, _, _, _, _, _, _, _, _, _, _, I, I, I }, // 9
		{ I, I, I, I, _, _, _, _, _, _, _, _, _, _, _, I, I }, // 10
		{ I, I, I, I, _, _, _, _, _, _, _, _, _, _, _, _, I }, // 11
		{ I, I, I, I, _, _, _, _, _, _, _, _, _, _, _, _, _ }, // 12
		{ I, I, I, I, I, I, I, I, I, W, W, W, W, I, I, I, I }, // 13
		{ I, I, I, I, I, I, I, I, I, I, W, W, W, I, I, I, I }, // 14
		{ I, I, I, I, I, I, I, I, I, I, I, W, W, I, I, I, I }, // 15
		{ I, I, I, I, I, I, I, I, I, I, I, I, W, I, I, I, I }, // 16

	};
	
	private List<Operation> getInitialOperations() {
		List<Operation> operations = Lists.newArrayList();
		operations.add(new Set(turn, BLACK));
		operations.add(new Set(board, INIT_BOARD));
		return operations;
	}

	@Test
	public void testInitialMove() {
		assertMoveOk(move(bId, emptyState, getInitialOperations()));
	}

	@Test
	public void testInitialMoveByWrongPlayer() {
		assertHacker(move(wId, emptyState, getInitialOperations()));
	}

	@Test
	public void testInitialMoveFromNonEmptyState() {
		assertHacker(move(bId, nonEmptyState, getInitialOperations()));
	}

	@Test
	public void testInitialMoveWithExtraOperation() {
		List<Operation> initialOperations = getInitialOperations();
		initialOperations.add(new Set(board, INIT_BOARD));
		assertHacker(move(bId, emptyState, initialOperations));
	}
	
	Gamer[][] before = {
			// 0  1  2  3  4  5  6  7  8  9  10 11 12 13 14 15 16 
			{ I, I, I, I, _, I, I, I, I, I, I, I, I, I, I, I, I }, // 0
			{ I, I, I, I, _, B, I, I, I, I, I, I, I, I, I, I, I }, // 1
			{ I, I, I, I, _, _, B, I, I, I, I, I, I, I, I, I, I }, // 2
			{ I, I, I, I, B, _, B, B, I, I, I, I, I, I, I, I, I }, // 3
			{ _, _, _, _, _, _, B, _, _, _, _, _, _, I, I, I, I }, // 4
			{ I, _, _, _, _, _, B, B, _, _, _, _, _, I, I, I, I }, // 5
			{ I, I, _, _, _, _, _, B, _, _, _, _, _, I, I, I, I }, // 6
			{ I, I, I, _, _, _, _, _, _, _, _, _, _, I, I, I, I }, // 7
			{ I, I, I, I, _, _, _, _, W, W, _, _, _, I, I, I, I }, // 8
			{ I, I, I, I, _, _, _, _, B, W, _, _, _, _, I, I, I }, // 9
			{ I, I, I, I, _, _, _, _, _, W, _, _, _, _, _, I, I }, // 10
			{ I, I, I, I, _, _, _, _, _, _, W, _, _, _, _, _, I }, // 11
			{ I, I, I, I, _, _, _, _, _, W, _, _, _, _, _, _, _ }, // 12
			{ I, I, I, I, I, I, I, I, I, W, _, W, _, I, I, I, I }, // 13
			{ I, I, I, I, I, I, I, I, I, I, W, W, _, I, I, I, I }, // 14
			{ I, I, I, I, I, I, I, I, I, I, I, _, _, I, I, I, I }, // 15
			{ I, I, I, I, I, I, I, I, I, I, I, I, _, I, I, I, I }, // 16
	};
	
	Gamer[][] after = {
			// 0  1  2  3  4  5  6  7  8  9  10 11 12 13 14 15 16 
			{ I, I, I, I, _, I, I, I, I, I, I, I, I, I, I, I, I }, // 0
			{ I, I, I, I, _, B, I, I, I, I, I, I, I, I, I, I, I }, // 1
			{ I, I, I, I, _, _, B, I, I, I, I, I, I, I, I, I, I }, // 2
			{ I, I, I, I, B, _, B, B, I, I, I, I, I, I, I, I, I }, // 3
			{ _, _, _, _, _, _, B, _, _, _, _, _, _, I, I, I, I }, // 4
			{ I, _, _, _, _, _, B, B, _, _, _, _, _, I, I, I, I }, // 5
			{ I, I, _, _, _, _, _, _, _, _, _, _, _, I, I, I, I }, // 6
			{ I, I, I, _, _, _, _, B, _, _, _, _, _, I, I, I, I }, // 7
			{ I, I, I, I, _, _, _, _, W, W, _, _, _, I, I, I, I }, // 8
			{ I, I, I, I, _, _, _, _, B, W, _, _, _, _, I, I, I }, // 9
			{ I, I, I, I, _, _, _, _, _, W, _, _, _, _, _, I, I }, // 10
			{ I, I, I, I, _, _, _, _, _, _, W, _, _, _, _, _, I }, // 11
			{ I, I, I, I, _, _, _, _, _, W, _, _, _, _, _, _, _ }, // 12
			{ I, I, I, I, I, I, I, I, I, W, _, W, _, I, I, I, I }, // 13
			{ I, I, I, I, I, I, I, I, I, I, W, W, _, I, I, I, I }, // 14
			{ I, I, I, I, I, I, I, I, I, I, I, _, _, I, I, I, I }, // 15
			{ I, I, I, I, I, I, I, I, I, I, I, I, _, I, I, I, I }, // 16
	};
	
	private final Map<String, Object> turnOfBSingleMove = ImmutableMap.<String, Object>of(
			turn, BLACK,
			board, before);
	
	private final List<Operation> singleMoveOfB = ImmutableList.<Operation>of(
			new Set(turn, WHITE),
			new Set(board, after));
	
	@Test
	public void testBlackSingleMove() {
		assertMoveOk(move(bId, turnOfBSingleMove, singleMoveOfB));
	}
	
	Gamer[][] before2 = {
			// 0  1  2  3  4  5  6  7  8  9  10 11 12 13 14 15 16 
			{ I, I, I, I, _, I, I, I, I, I, I, I, I, I, I, I, I }, // 0
			{ I, I, I, I, _, B, I, I, I, I, I, I, I, I, I, I, I }, // 1
			{ I, I, I, I, _, _, B, I, I, I, I, I, I, I, I, I, I }, // 2
			{ I, I, I, I, B, _, B, B, I, I, I, I, I, I, I, I, I }, // 3
			{ _, _, _, _, _, _, B, _, _, _, _, _, _, I, I, I, I }, // 4
			{ I, _, _, _, _, _, B, B, _, _, _, _, _, I, I, I, I }, // 5
			{ I, I, _, _, _, _, _, B, _, _, _, _, _, I, I, I, I }, // 6
			{ I, I, I, _, _, _, _, _, _, _, _, _, _, I, I, I, I }, // 7
			{ I, I, I, I, _, _, _, _, W, W, _, _, _, I, I, I, I }, // 8
			{ I, I, I, I, _, _, _, _, B, W, _, _, _, _, I, I, I }, // 9
			{ I, I, I, I, _, _, _, _, _, W, _, _, _, _, _, I, I }, // 10
			{ I, I, I, I, _, _, _, _, _, _, W, _, _, _, _, _, I }, // 11
			{ I, I, I, I, _, _, _, _, _, W, _, _, _, _, _, _, _ }, // 12
			{ I, I, I, I, I, I, I, I, I, W, _, W, _, I, I, I, I }, // 13
			{ I, I, I, I, I, I, I, I, I, I, W, W, _, I, I, I, I }, // 14
			{ I, I, I, I, I, I, I, I, I, I, I, _, _, I, I, I, I }, // 15
			{ I, I, I, I, I, I, I, I, I, I, I, I, _, I, I, I, I }, // 16
	};
	
	Gamer[][] after2 = {
			// 0  1  2  3  4  5  6  7  8  9  10 11 12 13 14 15 16 
			{ I, I, I, I, _, I, I, I, I, I, I, I, I, I, I, I, I }, // 0
			{ I, I, I, I, _, B, I, I, I, I, I, I, I, I, I, I, I }, // 1
			{ I, I, I, I, _, _, B, I, I, I, I, I, I, I, I, I, I }, // 2
			{ I, I, I, I, B, _, B, B, I, I, I, I, I, I, I, I, I }, // 3
			{ _, _, _, _, _, _, B, _, _, _, _, _, _, I, I, I, I }, // 4
			{ I, _, _, _, _, _, B, B, _, W, _, _, _, I, I, I, I }, // 5
			{ I, I, _, _, _, _, _, B, _, _, _, _, _, I, I, I, I }, // 6
			{ I, I, I, _, _, _, _, _, _, _, _, _, _, I, I, I, I }, // 7
			{ I, I, I, I, _, _, _, _, W, _, _, _, _, I, I, I, I }, // 8
			{ I, I, I, I, _, _, _, _, B, W, _, _, _, _, I, I, I }, // 9
			{ I, I, I, I, _, _, _, _, _, W, _, _, _, _, _, I, I }, // 10
			{ I, I, I, I, _, _, _, _, _, _, W, _, _, _, _, _, I }, // 11
			{ I, I, I, I, _, _, _, _, _, W, _, _, _, _, _, _, _ }, // 12
			{ I, I, I, I, I, I, I, I, I, W, _, W, _, I, I, I, I }, // 13
			{ I, I, I, I, I, I, I, I, I, I, W, W, _, I, I, I, I }, // 14
			{ I, I, I, I, I, I, I, I, I, I, I, _, _, I, I, I, I }, // 15
			{ I, I, I, I, I, I, I, I, I, I, I, I, _, I, I, I, I }, // 16
	};
	
	private final Map<String, Object> turnOfWSingleMove = ImmutableMap.<String, Object>of(
			turn, WHITE,
			board, before2);
	
	private final List<Operation> singleMoveOfW = ImmutableList.<Operation>of(
			new Set(turn, BLACK),
			new Set(board, after2));
	
	@Test
	public void testWhiteIllegalMove() {
		assertHacker(move(wId, turnOfWSingleMove, singleMoveOfW));
	}
}