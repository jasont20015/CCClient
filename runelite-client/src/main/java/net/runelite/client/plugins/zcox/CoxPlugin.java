package net.runelite.client.plugins.zcox;

import com.google.inject.Provides;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.inject.Inject;
import net.runelite.api.Client;
import net.runelite.api.GraphicsObject;
import net.runelite.api.MessageNode;
import net.runelite.api.NPC;
import net.runelite.api.Varbits;
import net.runelite.api.coords.WorldPoint;
import net.runelite.api.events.ChatMessage;
import net.runelite.api.events.GameTick;
import net.runelite.api.events.NpcDespawned;
import net.runelite.api.events.NpcSpawned;
import net.runelite.client.chat.ChatMessageManager;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;

@PluginDescriptor(
        name = "<html><font color=\"Lime\">Chambers of Xeric</font></html>",
        description = "All-in-one plugin for Chambers of Xeric",
        tags = {"CoX"},
        enabledByDefault = false
)
public class CoxPlugin extends Plugin {
    private static final int GAMEOBJECT_ID_PSN = 30032;
    private static final int GRAPHICSOBJECT_ID_CRYSTAL = 1447;
    private static final int GRAPHICSOBJECT_ID_HEAL = 1363;
    private static final int ANIMATION_ID_G1 = 430;
    private boolean needOlm = false;
    private int guardTick = -1;
    private boolean runGuard = false;
    int sleepcount = 0;
    private NPC Guard1_NPC;
    private NPC Guard2_NPC;
    private boolean runMutta;
    private NPC Mutta_NPC;
    private NPC Momma_NPC;
    private int OlmPhase = 0;
    private boolean runTekton;
    private NPC Tekton_NPC;
    private boolean runVanguards;
    private NPC meleeVanguard_NPC;
    private NPC mageVanguard_NPC;
    private NPC rangeVanguard_NPC;
    private boolean runOlm;
    private NPC Olm_NPC;
    private NPC OlmMelee_NPC;
    private int Olm_TicksUntilAction = -1;
    private int Olm_ActionCycle = -1;
    private int Olm_NextSpec = -1;
    private List<WorldPoint> Olm_Crystals = new ArrayList();
    private List<WorldPoint> Olm_Heal = new ArrayList();
    private List<WorldPoint> Olm_PSN = new ArrayList();
    @Inject
    private ChatMessageManager chatMessageManager;
    @Inject
    private Client client;
    @Inject
    private OverlayManager overlayManager;
    @Inject
    private CoxOverlay overlay;
    @Inject
    private CoxConfig config;

    public CoxPlugin() {
    }

    @Provides
    CoxConfig getConfig(ConfigManager configManager) {
        return (CoxConfig)configManager.getConfig(CoxConfig.class);
    }

    protected void startUp() {
        this.overlayManager.add(this.overlay);
    }

    protected void shutDown() {
        this.overlayManager.remove(this.overlay);
    }

    @Subscribe
    public void onChatMessage(ChatMessage chatMessage) {
        MessageNode messageNode = chatMessage.getMessageNode();
        if (messageNode.getValue().toLowerCase().contains("The Great Olm rises with the power of".toLowerCase()) || messageNode.getValue().toLowerCase().contains("!olm".toLowerCase())) {
            System.out.println("finding Olm NPC");
            if (!this.runOlm) {
                this.Olm_ActionCycle = -1;
                this.Olm_TicksUntilAction = 4;
            } else {
                this.Olm_ActionCycle = -1;
                this.Olm_TicksUntilAction = 3;
            }

            this.OlmPhase = 0;
            this.runOlm = true;
            this.needOlm = true;
            this.Olm_NextSpec = -1;
        }

        if (messageNode.getValue().toLowerCase().contains("The Great Olm is giving its all. this is its final stand".toLowerCase())) {
            System.out.println("finding Olm NPC");
            if (!this.runOlm) {
                this.Olm_ActionCycle = -1;
                this.Olm_TicksUntilAction = 4;
            } else {
                this.Olm_ActionCycle = -1;
                this.Olm_TicksUntilAction = 3;
            }

            this.OlmPhase = 1;
            System.out.println("OLM PHASE:" + this.OlmPhase);
            this.runOlm = true;
            this.needOlm = true;
            this.Olm_NextSpec = -1;
        }

    }

    @Subscribe
    public void onNpcSpawned(NpcSpawned npcSpawned) {
        NPC npc = npcSpawned.getNpc();
        switch(npc.getId()) {
            case 7540:
            case 7541:
            case 7542:
            case 7543:
            case 7544:
            case 7545:
                this.runTekton = true;
                this.Tekton_NPC = npc;
            case 7546:
            case 7547:
            case 7548:
            case 7549:
            case 7550:
            case 7551:
            case 7552:
            case 7553:
            case 7554:
            case 7555:
            case 7556:
            case 7557:
            case 7558:
            case 7559:
            case 7560:
            case 7564:
            case 7565:
            case 7566:
            case 7567:
            case 7568:
            default:
                break;
            case 7561:
                this.Momma_NPC = npc;
                break;
            case 7562:
                this.runMutta = true;
                this.Mutta_NPC = npc;
                break;
            case 7563:
                this.runMutta = true;
                this.Momma_NPC = npc;
                break;
            case 7569:
                this.Guard1_NPC = npc;
                this.guardTick = -1;
                this.runGuard = true;
                break;
            case 7570:
                this.Guard2_NPC = npc;
                this.guardTick = -1;
                this.runGuard = true;
        }

    }

    @Subscribe
    public void onNpcDespawned(NpcDespawned npcDespawned) {
        NPC npc = npcDespawned.getNpc();
        switch(npc.getId()) {
            case 7540:
            case 7541:
            case 7542:
            case 7543:
            case 7544:
            case 7545:
                this.runTekton = false;
                this.Tekton_NPC = null;
            case 7546:
            case 7547:
            case 7548:
            case 7549:
            case 7550:
            case 7551:
            case 7552:
            case 7553:
            case 7554:
            case 7555:
            case 7556:
            case 7557:
            case 7558:
            case 7559:
            case 7560:
            case 7564:
            case 7565:
            case 7566:
            case 7567:
            case 7568:
            default:
                break;
            case 7561:
                this.Momma_NPC = null;
                break;
            case 7562:
                this.Mutta_NPC = null;
                break;
            case 7563:
                this.runMutta = false;
                this.Momma_NPC = null;
                break;
            case 7569:
                this.Guard1_NPC = null;
                this.runGuard = false;
                this.Guard2_NPC = null;
                break;
            case 7570:
                this.Guard2_NPC = null;
                this.Guard1_NPC = null;
                this.runGuard = false;
                break;
            case 7571:
            case 7572:
                this.Guard1_NPC = null;
                this.runGuard = false;
                this.Guard2_NPC = null;
        }

    }

    @Subscribe
    public void onGameTick(GameTick event) {
        if (this.client.getVar(Varbits.IN_RAID) == 0) {
            this.runOlm = false;
            this.runGuard = false;
            this.runMutta = false;
            this.runTekton = false;
            this.needOlm = false;
            this.OlmPhase = 0;
            this.sleepcount = 0;
            this.Olm_Heal.clear();
        }

        Iterator var2;
        if (this.needOlm = true) {
            var2 = this.client.getNpcs().iterator();

            while(var2.hasNext()) {
                NPC monster = (NPC)var2.next();
                if (monster.getId() == 7551) {
                    this.needOlm = false;
                    this.Olm_NPC = monster;
                    System.out.println("Found olm Npc");
                    break;
                }
            }
        }

        if (this.runGuard) {
            if (this.guardTick == -1) {
                if (this.Guard1_NPC != null && this.Guard1_NPC.getAnimation() == 430) {
                    this.guardTick = 5;
                }

                if (this.Guard2_NPC != null && this.Guard2_NPC.getAnimation() == 430) {
                    this.guardTick = 5;
                }
            } else {
                --this.guardTick;
            }

            if (this.guardTick == 0) {
                this.guardTick = 5;
            }
        }

        if (this.runOlm) {
            this.Olm_Crystals.clear();
            this.Olm_Heal.clear();
            --this.sleepcount;
            if (this.Olm_TicksUntilAction == 1) {
                if (this.Olm_ActionCycle == 1) {
                    this.Olm_ActionCycle = 4;
                    this.Olm_TicksUntilAction = 4;
                    if (this.Olm_NextSpec == 1) {
                        if (this.OlmPhase == 1) {
                            this.Olm_NextSpec = 4;
                        } else {
                            this.Olm_NextSpec = 3;
                        }
                    } else {
                        --this.Olm_NextSpec;
                    }
                } else {
                    if (this.Olm_ActionCycle != -1) {
                        --this.Olm_ActionCycle;
                    }

                    this.Olm_TicksUntilAction = 4;
                }
            } else {
                --this.Olm_TicksUntilAction;
            }

            var2 = this.client.getGraphicsObjects().iterator();

            while(var2.hasNext()) {
                GraphicsObject o = (GraphicsObject)var2.next();
                if (o.getId() == 1447) {
                    for(int x = -1; x <= 1; ++x) {
                        for(int y = -1; y <= 1; ++y) {
                            WorldPoint newloc = WorldPoint.fromLocal(this.client, o.getLocation());
                            if (this.config.LargeCrystals()) {
                                newloc = newloc.dx(x);
                                newloc = newloc.dy(y);
                            }

                            this.Olm_Crystals.add(newloc);
                        }
                    }
                }

                if (this.sleepcount <= 0) {
                    if (o.getId() == 1338) {
                        this.Olm_TicksUntilAction = 1;
                        this.Olm_NextSpec = 2;
                        this.Olm_ActionCycle = 4;
                        this.sleepcount = 5;
                        System.out.println("setting off 1338 id - crystals");
                    }

                    if (o.getId() == 1356) {
                        this.Olm_TicksUntilAction = 4;
                        this.Olm_NextSpec = 1;
                        this.Olm_ActionCycle = 4;
                        this.sleepcount = 50;
                        System.out.println("setting off 1338 id - lighning");
                    }
                }

                if (o.getId() == 1363) {
                    this.Olm_Heal.add(WorldPoint.fromLocal(this.client, o.getLocation()));
                }
            }
        }

    }

    int getGuardTick() {
        return this.guardTick;
    }

    boolean isRunGuard() {
        return this.runGuard;
    }

    NPC getGuard1_NPC() {
        return this.Guard1_NPC;
    }

    NPC getGuard2_NPC() {
        return this.Guard2_NPC;
    }

    boolean isRunMutta() {
        return this.runMutta;
    }

    NPC getMutta_NPC() {
        return this.Mutta_NPC;
    }

    NPC getMomma_NPC() {
        return this.Momma_NPC;
    }

    int getOlmPhase() {
        return this.OlmPhase;
    }

    boolean isRunTekton() {
        return this.runTekton;
    }

    NPC getTekton_NPC() {
        return this.Tekton_NPC;
    }

    boolean isRunVanguards() {
        return this.runVanguards;
    }

    NPC getMeleeVanguard_NPC() {
        return this.meleeVanguard_NPC;
    }

    NPC getMageVanguard_NPC() {
        return this.mageVanguard_NPC;
    }

    NPC getRangeVanguard_NPC() {
        return this.rangeVanguard_NPC;
    }

    boolean isRunOlm() {
        return this.runOlm;
    }

    NPC getOlm_NPC() {
        return this.Olm_NPC;
    }

    NPC getOlmMelee_NPC() {
        return this.OlmMelee_NPC;
    }

    int getOlm_TicksUntilAction() {
        return this.Olm_TicksUntilAction;
    }

    int getOlm_ActionCycle() {
        return this.Olm_ActionCycle;
    }

    int getOlm_NextSpec() {
        return this.Olm_NextSpec;
    }

    List<WorldPoint> getOlm_Crystals() {
        return this.Olm_Crystals;
    }

    List<WorldPoint> getOlm_Heal() {
        return this.Olm_Heal;
    }

    List<WorldPoint> getOlm_PSN() {
        return this.Olm_PSN;
    }
}
