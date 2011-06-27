/**
 * Simluation that models a single agent in a world where it is unable to
 * hunt enough food to survive
 */
public class SingleAgent extends GenericSimulation
{

	public SingleAgent()
	{
		super("Single agent starving to death", 100, 0, 0);
	}

	@Override
	protected void foods()
	{
		addFood("Rabbit", 1, 1);
		addFood("Chicken", 2, 1);
	}

	@Override
	protected void agents()
	{
		addAgent(new TestAgent(20, 5));
	}

	@Override
	protected void groups()
	{
	}

	@Override
	protected Class<? extends AbstractFreeAgentGroup> chooseFreeAgentHandler()
	{
		return null;
	}

	@Override
	protected void plugins()
	{
		addPlugin(new DebugSwitchPlugin());
		addPlugin(new HuntersAlivePlugin(getPath() + "/population.png", 1500, 1200));
	}

	@Override
	protected void events()
	{
	}

}

