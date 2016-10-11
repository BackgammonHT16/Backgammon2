/**
 * 
 */
package controller;

/**
 * @author philipp
 *
 */
public class Compare  extends State {

	public Compare(GameEngine engine) {
		super(engine);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void nextAction() {
		if(engine.getDice().getValue(0) > engine.getDice().getValue(1))
		{
			engine.nextPlayer();
		}
		engine.setState(new PickStart(engine));
	}
}
