package net.runelite.client.plugins.zcox;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.util.Iterator;
import java.util.List;
import javax.inject.Inject;
import net.runelite.api.Client;
import net.runelite.api.NPC;
import net.runelite.api.NPCComposition;
import net.runelite.api.Perspective;
import net.runelite.api.Point;
import net.runelite.api.coords.LocalPoint;
import net.runelite.api.coords.WorldArea;
import net.runelite.api.coords.WorldPoint;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.OverlayPriority;
import net.runelite.client.ui.overlay.OverlayUtil;

public class CoxOverlay extends Overlay {
    private final Client client;
    private final CoxPlugin plugin;
    private final CoxConfig config;

    @Inject
    private CoxOverlay(Client client, CoxPlugin plugin, CoxConfig config) {
        this.client = client;
        this.plugin = plugin;
        this.config = config;
        this.setPosition(OverlayPosition.DYNAMIC);
        this.setPriority(OverlayPriority.HIGH);
        this.setLayer(OverlayLayer.ABOVE_SCENE);
    }

    public Dimension render(Graphics2D graphics) {
        NPC boss;
        NPC G2;
        int cycle;
        if (this.plugin.isRunMutta() && this.config.Muttadile()) {
            boss = this.plugin.getMomma_NPC();
            G2 = this.plugin.getMutta_NPC();
            boolean size;
            NPCComposition compositionbaby;
            List meleeRange;
            Iterator var7;
            WorldPoint p;
            if (boss != null) {
                size = true;
                compositionbaby = boss.getTransformedComposition();
                cycle = compositionbaby.getSize();
                meleeRange = this.getHitSquares(boss.getWorldLocation(), cycle, 1, false);
                var7 = meleeRange.iterator();

                while(var7.hasNext()) {
                    p = (WorldPoint)var7.next();
                    this.drawTile(graphics, p, Color.RED, 2, 155, 10);
                }
            }

            if (G2 != null) {
                size = true;
                compositionbaby = G2.getTransformedComposition();
                cycle = compositionbaby.getSize();
                meleeRange = this.getHitSquares(G2.getWorldLocation(), cycle, 1, false);
                var7 = meleeRange.iterator();

                while(var7.hasNext()) {
                    p = (WorldPoint)var7.next();
                    this.drawTile(graphics, p, Color.RED, 1, 155, 10);
                }
            }
        }

        int spec;
        if (this.plugin.isRunGuard() && this.config.Guardians()) {
            boss = this.plugin.getGuard1_NPC();
            G2 = this.plugin.getGuard2_NPC();
            cycle = this.plugin.getGuardTick();
            if (cycle == 5) {
                WorldPoint p;
                boolean size;
                NPCComposition composition;
                List meleeRange;
                Iterator var24;
                if (boss != null) {
                    size = true;
                    composition = boss.getTransformedComposition();
                    spec = composition.getSize();
                    meleeRange = this.getHitSquares(boss.getWorldLocation(), spec, 1, true);
                    var24 = meleeRange.iterator();

                    while(var24.hasNext()) {
                        p = (WorldPoint)var24.next();
                        this.drawTile(graphics, p, Color.GREEN, 1, 155, 10);
                    }
                }

                if (G2 != null) {
                    size = true;
                    composition = G2.getTransformedComposition();
                    spec = composition.getSize();
                    meleeRange = this.getHitSquares(G2.getWorldLocation(), spec, 1, true);
                    var24 = meleeRange.iterator();

                    while(var24.hasNext()) {
                        p = (WorldPoint)var24.next();
                        this.drawTile(graphics, p, Color.GREEN, 1, 155, 10);
                    }
                }
            }
        }

        int tick;
        if (this.plugin.isRunTekton() && this.config.Tekton()) {
            boss = this.plugin.getTekton_NPC();
            if (boss != null) {
                int size = 1;
                NPCComposition composition = boss.getTransformedComposition();
                tick = composition.getSize();
                List<WorldPoint> meleeRange = this.getHitSquares(boss.getWorldLocation(), tick, 1, false);
                Iterator var20 = meleeRange.iterator();

                while(var20.hasNext()) {
                    WorldPoint p = (WorldPoint)var20.next();
                    this.drawTile(graphics, p, Color.WHITE, 1, 155, 10);
                }
            }
        }

        if (this.plugin.isRunOlm()) {
            boss = this.plugin.getOlm_NPC();
            if (this.config.OlmCrystals()) {
                Iterator var13 = this.plugin.getOlm_Crystals().iterator();

                WorldPoint p;
                while(var13.hasNext()) {
                    p = (WorldPoint)var13.next();
                    this.drawTile(graphics, p, Color.RED, 1, 255, 0);
                }

                var13 = this.plugin.getOlm_Heal().iterator();

                while(var13.hasNext()) {
                    p = (WorldPoint)var13.next();
                    this.drawTile(graphics, p, Color.BLUE, 3, 255, 0);
                }

                var13 = this.plugin.getOlm_PSN().iterator();

                while(var13.hasNext()) {
                    p = (WorldPoint)var13.next();
                    this.drawTile(graphics, p, Color.GREEN, 3, 255, 0);
                }
            }

            if (this.config.OlmSpec()) {
            }

            if (this.config.OlmTick() && boss != null) {
                String tickStr;
                String cycleStr;
                tick = this.plugin.getOlm_TicksUntilAction();
                cycle = this.plugin.getOlm_ActionCycle();
                spec = this.plugin.getOlm_NextSpec();
                tickStr = String.valueOf(tick);
                cycleStr = "?";
                label62:
                switch(cycle) {
                    case -1:
                        cycleStr = "??";
                    case 0:
                    default:
                        break;
                    case 1:
                        switch(spec) {
                            case -1:
                                cycleStr = "??";
                            case 0:
                            default:
                                break label62;
                            case 1:
                                cycleStr = "Portals";
                                break label62;
                            case 2:
                                cycleStr = "lightning";
                                break label62;
                            case 3:
                                cycleStr = "Crystals";
                                break label62;
                            case 4:
                                cycleStr = "Heal";
                                break label62;
                        }
                    case 2:
                        cycleStr = "Sauto";
                        break;
                    case 3:
                        cycleStr = "Null";
                        break;
                    case 4:
                        cycleStr = "Nauto";
                }

                String combinedStr = cycleStr + ":" + tickStr;
                Point canvasPoint = boss.getCanvasTextLocation(graphics, combinedStr, 130);
                this.renderTextLocation(graphics, combinedStr, 12, 1, Color.WHITE, canvasPoint);
            }
        }

        return null;
    }

    private void drawTile(Graphics2D graphics, WorldPoint point, Color color, int strokeWidth, int outlineAlpha, int fillAlpha) {
        WorldPoint playerLocation = this.client.getLocalPlayer().getWorldLocation();
        if (point.distanceTo(playerLocation) < 32) {
            LocalPoint lp = LocalPoint.fromWorld(this.client, point);
            if (lp != null) {
                Polygon poly = Perspective.getCanvasTilePoly(this.client, lp);
                if (poly != null) {
                    graphics.setColor(new Color(color.getRed(), color.getGreen(), color.getBlue(), outlineAlpha));
                    graphics.setStroke(new BasicStroke((float)strokeWidth));
                    graphics.draw(poly);
                    graphics.setColor(new Color(color.getRed(), color.getGreen(), color.getBlue(), fillAlpha));
                    graphics.fill(poly);
                }
            }
        }
    }

    private void renderNpcOverlay(Graphics2D graphics, NPC actor, Color color, int outlineWidth, int outlineAlpha, int fillAlpha) {
        int size = 1;
        NPCComposition composition = actor.getTransformedComposition();
        if (composition != null) {
            size = composition.getSize();
        }

        LocalPoint lp = actor.getLocalLocation();
        Polygon tilePoly = Perspective.getCanvasTileAreaPoly(this.client, lp, size);
        if (tilePoly != null) {
            graphics.setColor(new Color(color.getRed(), color.getGreen(), color.getBlue(), outlineAlpha));
            graphics.setStroke(new BasicStroke((float)outlineWidth));
            graphics.draw(tilePoly);
            graphics.setColor(new Color(color.getRed(), color.getGreen(), color.getBlue(), fillAlpha));
            graphics.fill(tilePoly);
        }

    }

    private void renderTextLocation(Graphics2D graphics, String txtString, int fontSize, int fontStyle, Color fontColor, Point canvasPoint) {
        graphics.setFont(new Font("Arial", fontStyle, fontSize));
        if (canvasPoint != null) {
            Point canvasCenterPoint = new Point(canvasPoint.getX(), canvasPoint.getY());
            Point canvasCenterPoint_shadow = new Point(canvasPoint.getX() + 1, canvasPoint.getY() + 1);
            OverlayUtil.renderTextLocation(graphics, canvasCenterPoint_shadow, txtString, Color.BLACK);
            OverlayUtil.renderTextLocation(graphics, canvasCenterPoint, txtString, fontColor);
        }

    }

    private List<WorldPoint> getHitSquares(WorldPoint npcLoc, int npcSize, int thickness, boolean includeUnder) {
        List<WorldPoint> little = (new WorldArea(npcLoc, npcSize, npcSize)).toWorldPointList();
        List<WorldPoint> big = (new WorldArea(npcLoc.getX() - thickness, npcLoc.getY() - thickness, npcSize + thickness * 2, npcSize + thickness * 2, npcLoc.getPlane())).toWorldPointList();
        if (!includeUnder) {
            Iterator it = big.iterator();

            while(it.hasNext()) {
                WorldPoint p = (WorldPoint)it.next();
                if (little.contains(p)) {
                    it.remove();
                }
            }
        }

        return big;
    }

    private void renderPoly(Graphics2D graphics, Color color, Polygon polygon) {
        if (polygon != null) {
            graphics.setColor(color);
            graphics.setStroke(new BasicStroke(2.0F));
            graphics.draw(polygon);
            graphics.setColor(new Color(color.getRed(), color.getGreen(), color.getBlue(), 20));
            graphics.fill(polygon);
        }

    }
}