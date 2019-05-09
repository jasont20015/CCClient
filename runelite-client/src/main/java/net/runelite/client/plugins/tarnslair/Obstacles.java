/*
 * Copyright (c) 2018, Shaun Dreclin <https://github.com/ShaunDreclin>
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
package net.runelite.client.plugins.tarnslair;

import com.google.common.collect.Sets;
import java.util.Set;
import static net.runelite.api.NullObjectID.NULL_20575;
import static net.runelite.api.ObjectID.FLOOR_20583;
import static net.runelite.api.ObjectID.FLOOR_20584;
import static net.runelite.api.ObjectID.FLOOR_20628;
import static net.runelite.api.ObjectID.FLOOR_20634;
import static net.runelite.api.ObjectID.FLOOR_20636;
import static net.runelite.api.ObjectID.HANGING_LOG_20571;
import static net.runelite.api.ObjectID.HANGING_LOG_20572;
import static net.runelite.api.ObjectID.HANGING_LOG_20573;
import static net.runelite.api.ObjectID.HANGING_LOG_20574;
import static net.runelite.api.ObjectID.PASSAGEWAY_15770;
import static net.runelite.api.ObjectID.PASSAGEWAY_15771;
import static net.runelite.api.ObjectID.PASSAGEWAY_15772;
import static net.runelite.api.ObjectID.PASSAGEWAY_15773;
import static net.runelite.api.ObjectID.PASSAGEWAY_15774;
import static net.runelite.api.ObjectID.PASSAGEWAY_16129;
import static net.runelite.api.ObjectID.PASSAGEWAY_16130;
import static net.runelite.api.ObjectID.PASSAGEWAY_16131;
import static net.runelite.api.ObjectID.PASSAGEWAY_16132;
import static net.runelite.api.ObjectID.PASSAGEWAY_16133;
import static net.runelite.api.ObjectID.PASSAGEWAY_16134;
import static net.runelite.api.ObjectID.PASSAGEWAY_18307;
import static net.runelite.api.ObjectID.PASSAGEWAY_18308;
import static net.runelite.api.ObjectID.PASSAGEWAY_18309;
import static net.runelite.api.ObjectID.PASSAGEWAY_18310;
import static net.runelite.api.ObjectID.PASSAGEWAY_18311;
import static net.runelite.api.ObjectID.PASSAGEWAY_20488;
import static net.runelite.api.ObjectID.PASSAGEWAY_20489;
import static net.runelite.api.ObjectID.PASSAGEWAY_20490;
import static net.runelite.api.ObjectID.PASSAGEWAY_20491;
import static net.runelite.api.ObjectID.PASSAGEWAY_20492;
import static net.runelite.api.ObjectID.PASSAGEWAY_20493;
import static net.runelite.api.ObjectID.PASSAGEWAY_20495;
import static net.runelite.api.ObjectID.PASSAGEWAY_20497;
import static net.runelite.api.ObjectID.PASSAGEWAY_20498;
import static net.runelite.api.ObjectID.PASSAGEWAY_20499;
import static net.runelite.api.ObjectID.PASSAGEWAY_20500;
import static net.runelite.api.ObjectID.PASSAGEWAY_20501;
import static net.runelite.api.ObjectID.PASSAGEWAY_20502;
import static net.runelite.api.ObjectID.PASSAGEWAY_20503;
import static net.runelite.api.ObjectID.PASSAGEWAY_20504;
import static net.runelite.api.ObjectID.PASSAGEWAY_20505;
import static net.runelite.api.ObjectID.PASSAGEWAY_20506;
import static net.runelite.api.ObjectID.PASSAGEWAY_20507;
import static net.runelite.api.ObjectID.PASSAGEWAY_20509;
import static net.runelite.api.ObjectID.PASSAGEWAY_20510;
import static net.runelite.api.ObjectID.PASSAGEWAY_20511;
import static net.runelite.api.ObjectID.PASSAGEWAY_20512;
import static net.runelite.api.ObjectID.PASSAGEWAY_20513;
import static net.runelite.api.ObjectID.PASSAGEWAY_20514;
import static net.runelite.api.ObjectID.PASSAGEWAY_20515;
import static net.runelite.api.ObjectID.PASSAGEWAY_20516;
import static net.runelite.api.ObjectID.PASSAGEWAY_20517;
import static net.runelite.api.ObjectID.PASSAGEWAY_20518;
import static net.runelite.api.ObjectID.PASSAGEWAY_20519;
import static net.runelite.api.ObjectID.PASSAGEWAY_20520;
import static net.runelite.api.ObjectID.PASSAGEWAY_20521;
import static net.runelite.api.ObjectID.PASSAGEWAY_20522;
import static net.runelite.api.ObjectID.PASSAGEWAY_20523;
import static net.runelite.api.ObjectID.PASSAGEWAY_20524;
import static net.runelite.api.ObjectID.PASSAGEWAY_20525;
import static net.runelite.api.ObjectID.PASSAGEWAY_20526;
import static net.runelite.api.ObjectID.PASSAGEWAY_20527;
import static net.runelite.api.ObjectID.PASSAGEWAY_20528;
import static net.runelite.api.ObjectID.PASSAGEWAY_20529;
import static net.runelite.api.ObjectID.PASSAGEWAY_20530;
import static net.runelite.api.ObjectID.PASSAGEWAY_20531;
import static net.runelite.api.ObjectID.PASSAGEWAY_20532;
import static net.runelite.api.ObjectID.PASSAGEWAY_20533;
import static net.runelite.api.ObjectID.PASSAGEWAY_20534;
import static net.runelite.api.ObjectID.PASSAGEWAY_20535;
import static net.runelite.api.ObjectID.PASSAGEWAY_20536;
import static net.runelite.api.ObjectID.PASSAGEWAY_20537;
import static net.runelite.api.ObjectID.PASSAGEWAY_20538;
import static net.runelite.api.ObjectID.PASSAGEWAY_20539;
import static net.runelite.api.ObjectID.STAIRS_17098;
import static net.runelite.api.ObjectID.STAIRS_17099;
import static net.runelite.api.ObjectID.STAIRS_18973;
import static net.runelite.api.ObjectID.STAIRS_18974;
import static net.runelite.api.ObjectID.WALL_20588;
import static net.runelite.api.ObjectID.WALL_20590;
import static net.runelite.api.ObjectID.WALL_20592;
import static net.runelite.api.ObjectID.WALL_20594;
import static net.runelite.api.ObjectID.WALL_20596;
import static net.runelite.api.ObjectID.WALL_20613;
import static net.runelite.api.ObjectID.WALL_20615;
import static net.runelite.api.ObjectID.WALL_20616;
import static net.runelite.api.ObjectID.WALL_20618;

class Obstacles
{
	static final Set<Integer> STAIRCASE_IDS = Sets.newHashSet(
		PASSAGEWAY_15770,	/*Wall staircase*/
		PASSAGEWAY_15771,	/*Wall staircase*/
		PASSAGEWAY_15772,	/*Wall staircase*/
		PASSAGEWAY_15773,	/*Wall staircase*/
		PASSAGEWAY_15774,	/*Wall staircase*/
		PASSAGEWAY_16129,	/*Wall staircase*/
		PASSAGEWAY_16130,	/*Wall staircase*/
		PASSAGEWAY_16131,	/*Wall staircase*/
		PASSAGEWAY_16132,	/*Wall staircase*/
		PASSAGEWAY_16133,	/*Wall staircase*/
		PASSAGEWAY_16134,	/*Wall staircase*/
		PASSAGEWAY_18307,   /*Wall staircase*/
		PASSAGEWAY_18308,   /*Wall staircase*/
		PASSAGEWAY_18309,   /*Wall staircase*/
		PASSAGEWAY_18310,   /*Wall staircase*/
		PASSAGEWAY_18311,   /*Wall staircase*/
		PASSAGEWAY_20488,	/*Wall staircase*/
		PASSAGEWAY_20489,	/*Wall staircase*/
		PASSAGEWAY_20490,	/*Wall staircase*/
		PASSAGEWAY_20491,	/*Wall staircase*/
		PASSAGEWAY_20492,	/*Wall staircase*/
		PASSAGEWAY_20493,	/*Wall staircase*/
		PASSAGEWAY_20495,	/*Wall staircase*/
		PASSAGEWAY_20497,	/*Wall staircase*/
		PASSAGEWAY_20498,	/*Wall staircase*/
		PASSAGEWAY_20499,	/*Wall staircase*/
		PASSAGEWAY_20500,	/*Wall staircase*/
		PASSAGEWAY_20501,	/*Wall staircase*/
		PASSAGEWAY_20502,	/*Wall staircase*/
		PASSAGEWAY_20503,	/*Wall staircase*/
		PASSAGEWAY_20504,	/*Wall staircase*/
		PASSAGEWAY_20505,	/*Wall staircase*/
		PASSAGEWAY_20506,	/*Wall staircase*/
		PASSAGEWAY_20506,	/*Wall staircase*/
		PASSAGEWAY_20507,	/*Wall staircase*/
		PASSAGEWAY_20509,	/*Wall staircase*/
		PASSAGEWAY_20510,	/*Wall staircase*/
		PASSAGEWAY_20511,	/*Wall staircase*/
		PASSAGEWAY_20512,	/*Wall staircase*/
		PASSAGEWAY_20513,	/*Wall staircase*/
		PASSAGEWAY_20514,	/*Wall staircase*/
		PASSAGEWAY_20515,	/*Wall staircase*/
		PASSAGEWAY_20516,	/*Wall staircase*/
		PASSAGEWAY_20517,	/*Wall staircase*/
		PASSAGEWAY_20518,	/*Wall staircase*/
		PASSAGEWAY_20519,	/*Wall staircase*/
		PASSAGEWAY_20520,	/*Wall staircase*/
		PASSAGEWAY_20521,	/*Wall staircase*/
		PASSAGEWAY_20522,	/*Wall staircase*/
		PASSAGEWAY_20523,	/*Wall staircase*/
		PASSAGEWAY_20524,	/*Wall staircase*/
		PASSAGEWAY_20525,	/*Wall staircase*/
		PASSAGEWAY_20526,	/*Wall staircase*/
		PASSAGEWAY_20527,	/*Wall staircase*/
		PASSAGEWAY_20528,	/*Wall staircase*/
		PASSAGEWAY_20529,	/*Wall staircase*/
		PASSAGEWAY_20530,	/*Wall staircase*/
		PASSAGEWAY_20531,	/*Wall staircase*/
		PASSAGEWAY_20532,	/*Wall staircase*/
		PASSAGEWAY_20533,	/*Wall staircase*/
		PASSAGEWAY_20534,	/*Wall staircase*/
		PASSAGEWAY_20535,	/*Wall staircase*/
		PASSAGEWAY_20536,	/*Wall staircase*/
		PASSAGEWAY_20537,	/*Wall staircase*/
		PASSAGEWAY_20538,	/*Wall staircase*/
		PASSAGEWAY_20539,	/*Wall staircase*/
		STAIRS_17098,		/*Floor staircase*/
		STAIRS_17099,		/*Floor staircase*/
		STAIRS_18973,		/*Floor staircase*/
		STAIRS_18974		/*Floor staircase*/
	);

	static final Set<Integer> WALL_TRAP_IDS = Sets.newHashSet(
		WALL_20590,		/*Wall spikes*/
		WALL_20592,		/*Wall spikes*/
		WALL_20594,		/*Wall spikes*/
		WALL_20596,		/*Wall spikes*/
		WALL_20588,		/*Wall spikes*/
		WALL_20613,		/*Wall pusher*/
		WALL_20615,		/*Wall pusher*/
		WALL_20616,		/*Wall pusher*/
		WALL_20618,		/*Wall pusher*/
		HANGING_LOG_20571,	/*Hanging log*/
		HANGING_LOG_20572,	/*Hanging log*/
		HANGING_LOG_20573,	/*Hanging log*/
		HANGING_LOG_20574	/*Hanging log*/
	);

	static final Set<Integer> FLOOR_TRAP_IDS = Sets.newHashSet(
		FLOOR_20583,	/*Floor spikes*/
		FLOOR_20584,	/*Floor spikes*/
		NULL_20575,		/*Floor spikes (visible)*/
		FLOOR_20628,	/*Trapdoor*/
		FLOOR_20634,	/*Floor button*/
		FLOOR_20636		/*Floor button*/
	);
}
