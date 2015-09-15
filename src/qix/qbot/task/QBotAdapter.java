package qix.qbot.task;

public class QBotAdapter extends QBot implements Router {

	@Override
	public Task sanityCheck() {
		return null;
	}

	@Override
	public Task hardReset() {
		return null;
	}

	@Override
	public Router getRouter() {
		return this;
	}

}
