package net.runelite.client.plugins.easy.pvm;

import java.awt.event.KeyEvent;
import javax.inject.Inject;
import net.runelite.api.Client;
import net.runelite.client.callback.ClientThread;
import net.runelite.client.input.KeyListener;
import net.runelite.client.plugins.menuentryswapper.MenuEntrySwapperPlugin;

public class ShiftClickInputListener implements KeyListener {
    @Inject
    private ClientThread clientThread;

    @Inject
    private Client client;

    @Inject
    private EasyPvmPlugin plugin;

    @Override
    public void keyTyped(KeyEvent event) {

    }

    @Override
    public void keyPressed(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.VK_SHIFT) {
            plugin.setShiftModifier(true);
        }
    }

    @Override
    public void keyReleased(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.VK_SHIFT) {
            plugin.setShiftModifier(false);
        }
    }
}