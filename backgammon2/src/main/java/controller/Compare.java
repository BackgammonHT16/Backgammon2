/**
 * 
 */
package controller;

/**
 * @author philipp
 *
 */
public class Compare  extends State {
	private boolean beenhere = false;

	public Compare(GameEngine engine) {
		super(engine);
	}

	@Override
	public void nextAction() {
		if(!beenhere)
		{
			beenhere = true;
			System.out.println("Compare");
		}
		if(engine.getDice().getValue(0) > engine.getDice().getValue(1))
		{
			engine.nextPlayer();
		}
		engine.setCurrentState();
		engine.getCurrentMove().activateSelectStart();
		engine.setState(new PickStart(engine));
	}
}
