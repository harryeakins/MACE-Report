/**
 * CLIPolitics Simulation
 * Cycles: 5000
 * Agents: 200 TestPoliticalAgents
 *  - 50 of each the core Strategy Types
 *  - Initial Food: 20
 *  - Default Consumption: 2
 *  - Beliefs: Randomised
 * Advice Consumption: 0.1
 * Free Group: BasicFreeAgentGroup
 * Groups: TestPoliticalGroup
 * Foods: Rabbit: 2 from 1, Stag: 10 from 2
 * Database: Remote: Corvette
 * Default seed: Passed as parameter
 */
public class CLIPolitics extends GenericSimulation
{
	public CLIPolitics(long rand)
	{
		super("Command Line Basic Politics Testing Bed " + rand, 5000, rand, 0.1);
	}

	@Override
	protected void agents()
	{
		Random randomGenerator = new Random(this.randomSeed);
		for (int i = 0; i < 50; i++)
		{
			addAgent(new TestPoliticalAgent(20, 2, AgentType.AC,
					randomGenerator.nextDouble(), randomGenerator.nextDouble()));
			addAgent(new TestPoliticalAgent(20, 2, AgentType.TFT,
					randomGenerator.nextDouble(), randomGenerator.nextDouble()));
			addAgent(new TestPoliticalAgent(20, 2, AgentType.AD,
					randomGenerator.nextDouble(), randomGenerator.nextDouble()));
			addAgent(new TestPoliticalAgent(20, 2, AgentType.R,
					randomGenerator.nextDouble(), randomGenerator.nextDouble()));
		}
	}

	@Override
	protected void foods()
	{
		addFood("Rabbit", 2, 1);
		addFood("Stag", 10, 2);
	}

	@Override
	protected void groups()
	{
		addGroup(TestPoliticalGroup.class);
	}

	@Override
	protected Class<? extends AbstractFreeAgentGroup> chooseFreeAgentHandler()
	{
		return BasicFreeAgentGroup.class;
	}

	@Override
	protected void plugins()
	{
		addPlugin(new HuntersAlivePlugin(getPath() + "/population.png", 1500, 1200));
		addPlugin(new DatabasePlugin(comment, true, true));
	}
}
