package de.skyl.coins.main;
// Coded By SkylTV //
// Copyright SkylTV //

import de.skyl.coins.api.MoneyAPI;
import de.skyl.coins.api.MoneyService;

public class Core {

    private Main main;
    public static Core core;
    private Manager manager;
    private MoneyAPI moneyAPI;
    public Core(Main main){
        core = this;
        this.main = main;
        manager = new Manager();
        moneyAPI = new MoneyService();
    }

    public void enableCore(){
        manager.registerListeners();
        manager.registerCommands();
    }
    public Main getMain() {
        return main;
    }

    public static Core getCore() {
        return core;
    }

    public static void setCore(Core core) {
        Core.core = core;
    }

    public Manager getManager() {
        return manager;
    }

    public MoneyAPI getMoneyAPI() {
        return moneyAPI;
    }
}
