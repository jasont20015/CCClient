/*
 * Copyright (c) 2017, Adam <Adam@sigterm.info>
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package net.runelite.client.plugins.projectilewarnings;

import com.google.inject.Binder;
import com.google.inject.Provides;
import net.runelite.api.Projectile;
import net.runelite.api.coords.LocalPoint;
import net.runelite.api.events.ProjectileMoved;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.EventBus;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayManager;


import javax.inject.Inject;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@PluginDescriptor(
        name="<html><font color=\"Lime\">Projectile Warnings</font></html>"
)
public class ProjectileWarningsPlugin extends Plugin
{
	private final Map<Projectile, AOEProjectile> projectiles = new HashMap<>();

	@Inject
	ProjectileWarningsOverlay overlay;

	@Inject
	private OverlayManager overlayManager;

	@Inject
	ProjectileWarningsConfig config;

	@Override
	public void configure(Binder binder)
	{
		binder.bind(ProjectileWarningsOverlay.class);
	}

	@Provides
	ProjectileWarningsConfig getConfig(ConfigManager configManager)
	{
		return configManager.getConfig(ProjectileWarningsConfig.class);
	}

	@Override
	protected void startUp() throws Exception
	{
		overlayManager.add(overlay);
	}

	@Override
	protected void shutDown() throws Exception
	{
		overlayManager.remove(overlay);
	}

	public Map<Projectile, AOEProjectile> getProjectiles()
	{
		return projectiles;
	}

	/**
	 * Called when a projectile is set to move towards a point. For
	 * projectiles that target the ground, like AoE projectiles from
	 * Lizardman Shamans, this is only called once
	 *
	 * @param event Projectile moved event
	 */
	@Subscribe
	public void onProjectileMoved(ProjectileMoved event)
	{
		Projectile projectile = event.getProjectile();

		// AoE projectiles do not target anything
//		if (projectile.getInteracting() != null)
//		{
//			return;
//		}

		int projectileId = projectile.getId();
		AOEProjectileInfo aoeProjectileInfo = AOEProjectileInfo.getById(projectileId);
		if (aoeProjectileInfo != null && isConfigEnabledForProjectileId(projectileId))
		{
			LocalPoint targetPoint = event.getPosition();
			AOEProjectile aoeProjectile = new AOEProjectile(Instant.now(), targetPoint, aoeProjectileInfo);
			projectiles.put(projectile, aoeProjectile);
		}
	}

	private boolean isConfigEnabledForProjectileId(int projectileId)
	{
		AOEProjectileInfo projectileInfo = AOEProjectileInfo.getById(projectileId);
		if (projectileInfo == null)
		{
			return false;
		}

		switch (projectileInfo)
		{
			case LIZARDMAN_SHAMAN_AOE:
				return config.isShamansEnabled();
			case CRAZY_ARCHAEOLOGIST_AOE:
				return config.isArchaeologistEnabled();
			case ICE_DEMON_RANGED_AOE:
			case ICE_DEMON_ICE_BARRAGE_AOE:
				return config.isIceDemonEnabled();
			case VASA_AWAKEN_AOE:
			case VASA_RANGED_AOE:
				return config.isVasaEnabled();
			case TEKTON_METEOR_AOE:
				return config.isTektonEnabled();
			case VORKATH_BOMB:
			case VORKATH_POISON_POOL:
			case VORKATH_SPAWN:
			case VORKATH_TICK_FIRE:
				return config.isVorkathEnabled();
			case VETION_LIGHTNING:
				return config.isVetionEnabled();
			case CHAOS_FANATIC:
				return config.isChaosFanaticEnabled();
			case GALVEK_BOMB:
			case GALVEK_MINE:
				return config.isGalvekEnabled();
			case DAWN_FREEZE:
			case DUSK_CEILING:
				return config.isGargBossEnabled();
			case OLM_FALLING_CRYSTAL:
			case OLM_BURNING:
				return config.isOlmEnabled();
			case CORPOREAL_BEAST:
			case CORPOREAL_BEAST_DARK_CORE:
				return config.isCorpEnabled();
			case WINTERTODT_SNOW_FALL:
				return config.isWintertodtEnabled();
			case XARPUS_ACID:
				return config.isXapusEnabled();
		}

		return false;
	}
}
