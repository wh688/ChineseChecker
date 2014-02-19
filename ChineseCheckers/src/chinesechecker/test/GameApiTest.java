package chinesechecker.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import chinesechecker.client.BoardArea;
import chinesechecker.client.ChineseCheckersLogic;
import chinesechecker.client.Color;
import chinesechecker.client.PlayerInfo;
import chinesechecker.client.Position;
import chinesechecker.client.State;
import chinesechecker.test.GameApi.AttemptChangeTokens;
import chinesechecker.test.GameApi.EndGame;
import chinesechecker.test.GameApi.GameReady;
import chinesechecker.test.GameApi.MakeMove;
import chinesechecker.test.GameApi.ManipulateState;
import chinesechecker.test.GameApi.ManipulationDone;
import chinesechecker.test.GameApi.Message;
import chinesechecker.test.GameApi.Operation;
import chinesechecker.test.GameApi.RequestManipulator;
import chinesechecker.test.GameApi.Set;
import chinesechecker.test.GameApi.SetRandomInteger;
import chinesechecker.test.GameApi.SetTurn;
import chinesechecker.test.GameApi.SetVisibility;
import chinesechecker.test.GameApi.Shuffle;
import chinesechecker.test.GameApi.UpdateUI;
import chinesechecker.test.GameApi.VerifyMove;
import chinesechecker.test.GameApi.VerifyMoveDone;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;

@RunWith(JUnit4.class)
public class GameApiTest {
	
	Map<String, Object> lastState = ImmutableMap.<String, Object>of("bc", 34, "key2", true);
	Set set = new Set("k", "sd");
	SetRandomInteger setRandomInteger = new SetRandomInteger("xcv", 23, 54);
	List<Operation> operations = Arrays.asList(set, setRandomInteger, set);
	
	
	PlayerInfo player1 = new PlayerInfo ("player1", Color.R, BoardArea.Area2);
	PlayerInfo player2 = new PlayerInfo ("player2", Color.B, BoardArea.Area5);
	PlayerInfo [] playerInfo = {player1, player2};
	State state = new State(playerInfo);
	
	ChineseCheckersLogic logic = new ChineseCheckersLogic();
	
	private void assertMoveOk(VerifyMove verifyMove) {
		logic.checkMoveIsLegal(verifyMove);
	}
	
	private void assertHacker(VerifyMove verifyMove) {
		VerifyMoveDone verifyDone = logic.verify(verifyMove);
		assertEquals(verifyMove.getLastMovePlayerId(), verifyDone.getHackerPlayerId());
	}
	
	private static final String PLAYER_ID = "playerId";
	private static final String R = "R"; // red hand
	private static final String B = "B"; // blue hand
	private static final String C = "Chessboard"; // Chessboard
	private static final String S = "State"; // State
	private final int rId = 1;
	private final int bId = 0;
	private final ImmutableMap<String, Object> rInfo = ImmutableMap.<String, Object>of(PLAYER_ID, rId);
	private final ImmutableMap<String, Object> bInfo = ImmutableMap.<String, Object>of(PLAYER_ID, bId);
	private final ImmutableList<Map<String, Object>> playersInfo = ImmutableList.<Map<String, Object>>of(rInfo, bInfo);
	private final ImmutableMap<String, Object> emptyState = ImmutableMap.<String, Object>of();
	private final ImmutableMap<String, Object> nonEmptyState = ImmutableMap.<String, Object>of("k", "v");
	
	
	private VerifyMove move(int lastMovePlayerId, Map<String, Object> lastState, State checkerstate, List<Operation> lastMove) {
		return new VerifyMove(playersInfo, emptyState, checkerstate, lastState, lastMove, lastMovePlayerId, ImmutableMap.<Integer, Integer>of());
	}
	
	private List<Operation> getInitialOperations() {
		return logic.getMoveInitial(state);
	}
	
	@Test
	public void testInitialMove() {
		assertMoveOk(move(rId, emptyState, state, getInitialOperations()));
	}
	
	
	@Test
	public void testInitialMoveByWrongPlayer() {
		assertHacker(move(bId, emptyState, state, getInitialOperations()));
	}
	
	@Test
	public void testInitialMoveFromNonEmptyState() {
		assertHacker(move(bId, nonEmptyState, state, getInitialOperations()));
	}
	
	
	@Test
	public void testInitialMoveWithExtraOperation() {
		List<Operation> initialOperations = getInitialOperations();
		initialOperations.add(new Set(C, state.getChessBoard().toString()));
		assertHacker(move(bId, emptyState, state, initialOperations));
	}	
	
	@Test
	public void testBlackSingleMove() {
		state.players[state.currentPlayIndex].SelectChess(new Position(4,5));
		state.players[state.currentPlayIndex].GoChess(new Position(5,5));
		ImmutableMap<String, Object> RedSingleMove = ImmutableMap.<String, Object>of(S, state);		
		List<Operation> singleMoveOfR = ImmutableList.<Operation>of(
				new SetTurn(state.currentPlayIndex),
				new Set(S, state));
		assertMoveOk(move(rId, RedSingleMove, state, singleMoveOfR));
	}
	
	
	List<Message> messages =
			Arrays.<Message>asList(
					set, setRandomInteger,
					new EndGame(32),
					new EndGame(ImmutableMap.of(42, -1232, 43, -5454)),
					new SetVisibility("sd"),
					new Shuffle(Lists.newArrayList("xzc", "zxc")),
					new GameReady(),
					new MakeMove(operations),
					new VerifyMoveDone(),
					new VerifyMoveDone(23, "asd"),
					new RequestManipulator(),
					new ManipulationDone(operations),
					new SetTurn(41),
					new SetTurn(41, 23),
					new AttemptChangeTokens(ImmutableMap.of(42, -1232, 43, -5454),
							ImmutableMap.of(42, 1232, 43, 5454))
					);
	@Test
	public void testSerialization() {
		for (Message equality : messages) {
			assertEquals(equality, Message.messageToHasEquality(equality.toMessage()));
		}
	}

	@Test
	public void testEquals() {
		for (Message equality : messages) {
			for (Message equalityOther : messages) {
				if (equality != equalityOther) {
					assertNotEquals(equality, equalityOther);
				}
			}
		}
	}
	@Test
	public void testLegalCheckHasJsonSupportedType() {
	    GameApi.checkHasJsonSupportedType(null);
	    GameApi.checkHasJsonSupportedType(34);
	    GameApi.checkHasJsonSupportedType(-342323);
	    GameApi.checkHasJsonSupportedType(5.23);
	    GameApi.checkHasJsonSupportedType("string");
	    GameApi.checkHasJsonSupportedType(true);
	    GameApi.checkHasJsonSupportedType(ImmutableList.of());
	    GameApi.checkHasJsonSupportedType(ImmutableList.of(true, 1));
	    GameApi.checkHasJsonSupportedType(ImmutableMap.of("key1", 1, "key2", false));
	    GameApi.checkHasJsonSupportedType(
	    		ImmutableMap.of("key1", 1, "key2", ImmutableList.of(true, 1)));
	}
	@Test
	public void testIllegalCheckHasJsonSupportedType() {
		checkIllegalJsonSupportedType(45L);
		checkIllegalJsonSupportedType(new Date());
		checkIllegalJsonSupportedType(Color.B);
	    checkIllegalJsonSupportedType(ImmutableList.of(true, Color.B));
	    checkIllegalJsonSupportedType(ImmutableMap.of(true, 1));
	    checkIllegalJsonSupportedType(ImmutableMap.of("key1", 1, "key2", Color.B));
	}
	
	private void checkIllegalJsonSupportedType(Object object) {
		try {
			GameApi.checkHasJsonSupportedType(object);
			fail();
		} catch (Exception expected) {
			// expected exception
		}
	}
}