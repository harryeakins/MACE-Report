public class PoliticsExtended extends GenericSimulation
{

	public PoliticsExtended()
	{
		super("Basic Politics Testing Bed", 500, 0, 0.1);
	}

	protected void agents()
	{
        Random randomGenerator = new Random();

		for (int i = 0; i < 20; i++)
		{
            addAgent(new PoliticalAgent(20, 2, AgentType.AC, 
              randomGenerator.nextDouble(), randomGenerator.nextDouble()));
			addAgent(new PoliticalAgent(20, 2, AgentType.TFT, 
			  randomGenerator.nextDouble(), randomGenerator.nextDouble()));
			addAgent(new PoliticalAgent(20, 2, AgentType.AD, 
			  randomGenerator.nextDouble(), randomGenerator.nextDouble()));
			addAgent(new PoliticalAgent(20, 2, AgentType.R, 
			  randomGenerator.nextDouble(), randomGenerator.nextDouble()));
		}

	}

	protected void foods()
	{
		addFood("Rabbit", 2, 1);
		addFood("Stag", 5, 2);
	}

	protected void groups()
	{
        addGroup(PoliticalGroup.class);
        addGroup(SpecialGroup.class);
	}

	protected Class<? extends AbstractFreeAgentGroup> chooseFreeAgentHandler()
	{
		return BasicFreeAgentGroup.class;
	}

	protected void plugins()
	{
		addPlugin(new DebugSwitchPlugin());
		addPlugin(new HuntersAlivePlugin(getPath() + "/population.png", 1500, 1200));
		addPlugin(new HunterListPlugin());
		addPlugin(new PoliticalCompassPlugin());
        addPlugin(new PoliticalCompass2Plugin());
        addPlugin(new CompassControl());
        addPlugin(new HunterInfo());
        addPlugin(new GroupAgentInfo());
        addPlugin(new GroupInfo());
        addPlugin(new GroupGraphs());
        addPlugin(new LoansInfoPolitics());                
	}

}

