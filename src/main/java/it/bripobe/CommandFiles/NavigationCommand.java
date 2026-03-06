package it.bripobe.CommandFiles;

public class NavigationCommand implements Command {

    private final String page;

    public NavigationCommand(String page) {
        this.page = page;
    }

    @Override
    public String execute() {
        return page;
    }
}
