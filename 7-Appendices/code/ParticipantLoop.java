private synchronized void doParticipants(long cycle)
{
	try
	{
		logger.log(Level.FINE, "***** Participants *****");

		if (activeParticipantIdSet.isEmpty()) return;
		String currentId = activeParticipantIdSet.first();
		TreeSet<String> done = new TreeSet<String>();

		while (done.size() < activeParticipantIdSet.size())
		{
			Participant currentParticipant = players.get(currentId);
			done.add(currentId);
			try
			{
				currentParticipant.execute();
			}
			catch (Exception e)
			{
				logger.log(Level.SEVERE, "Exception caused by " + currentId + " in method execute() ", e);
			}

			currentId = activeParticipantIdSet.higher(currentId);
			if (currentId == null) currentId = activeParticipantIdSet.first();
		}
	}
	catch (Exception e)
	{
		logger.log(Level.SEVERE, "Participants execute Error: ", e);
	}
}