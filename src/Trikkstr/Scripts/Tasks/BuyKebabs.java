package Trikkstr.Scripts.Tasks;

import Trikkstr.Scripts.GoblinKiller.Task;
import org.powerbot.script.Condition;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Component;
import org.powerbot.script.rt4.Npc;

import java.util.concurrent.Callable;

public class BuyKebabs extends Task
{

    Component clickToContinue = ctx.widgets.widget(231).component(2);
    Component clickToContinue2 = ctx.widgets.widget(217).component(2);
    Component yes = ctx.widgets.widget(219).component(0).component(2);

    final static int FOOD[] = { 315, 1971, 2309 };

    final static Tile karimsTile = new Tile(3273, 3180, 0);

    Npc karim;

    public BuyKebabs(ClientContext ctx)
    {
        super(ctx);
    }

    @Override
    public boolean activate()
    {
        return ctx.inventory.select().id(995).count(true) > 10
                && ctx.players.local().tile().x() > 3267;
    }

    @Override
    public void execute()
    {

        karim = detectKarim();
        //if(not near karim)
        //  step to karim
        System.out.println("Executing BuyKebabs.");

        if(ctx.players.local().tile().distanceTo(karim) > 4)
        {
            System.out.println("Walking over to Karim.");
            stepToKarim();
        }

        //if(coins > 10)
        //  buy kebab
        if(ctx.inventory.select().id(995).count(true) > 10)
        {
            System.out.println("Needs to buy a Kebab.");
            makePurchase();
        }
    }

    public void stepToKarim()
    {
        karim = detectKarim();

        ctx.movement.step(karimsTile);

        Condition.wait(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return ctx.players.local().inMotion();
            }
        }, 1000, 3);

        Condition.wait(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return !ctx.players.local().inMotion();
            }
        }, 1000, 8);

        System.out.println("Turning camera to Karim.");
        ctx.camera.turnTo(karim);
    }

    public void makePurchase()
    {

        karim = detectKarim();

        System.out.println("Talking to Karim.");
        karim.interact("Talk-to");

        Condition.wait(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return clickToContinue.visible();
            }
        }, 1000, 6);

        clickToContinue.click();

        Condition.wait(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return yes.visible();
            }
        }, 1000, 6);

        final int trigger = ctx.inventory.select().count();

        yes.click();

        Condition.wait(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return clickToContinue2.visible();
            }
        }, 1000, 6);

        clickToContinue2.click();

        Condition.wait(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return ctx.inventory.select().count() != trigger;
            }
        }, 1000, 6);
    }

    public Npc detectKarim()
    {
        return ctx.npcs.select().id(529).poll();
    }
}
