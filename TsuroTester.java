import org.junit.*;
import static org.junit.Assert.*;

/**
 * a tester class that tests the class Tsuro
 * @author TungHo Lin
 */

public class TsuroTester {

  //test getNextEndPoint()
  @Test
  public void testgetNextEndPoint() {
    assertEquals(2, Tsuro.getNextEndPoint(0));  //test 0
    assertEquals(3, Tsuro.getNextEndPoint(1));  //test 1
    assertEquals(7, Tsuro.getNextEndPoint(5));  //test many
  }
  
  //test getStoneStop()
  @Test
  public void testgetStoneStop() {
    TsuroButton b1 = new TsuroButton();
    b1.setConnections(TsuroButton.makeRandomConnectArray());
    int[] i = b1.getConnections();
    assertEquals(i[0], Tsuro.getStoneStop(b1, 0));  //test 0
    assertEquals(i[1], Tsuro.getStoneStop(b1, 1));  //test 1
    assertEquals(i[5], Tsuro.getStoneStop(b1, 5));  //test many
  }
  
  //Test getConnectEndpt()
  @Test
  public void testgetConnectEndpt() {
    assertEquals(4, Tsuro.getConnectEndpt(0));   //test 0
    assertEquals(5, Tsuro.getConnectEndpt(1));   //test 1
    assertEquals(3, Tsuro.getConnectEndpt(7));   //test many
  }
  
  //all other methods has to be tested when the game is running and therefore will be tested separately and recorded in the testing report
  
  
  
  
  
  
  
  
  
}