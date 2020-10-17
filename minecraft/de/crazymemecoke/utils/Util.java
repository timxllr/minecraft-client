package de.crazymemecoke.utils;

import com.mojang.authlib.Agent;
import com.mojang.authlib.exceptions.AuthenticationException;
import com.mojang.authlib.exceptions.InvalidCredentialsException;
import com.mojang.authlib.exceptions.UserMigratedException;
import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;
import com.mojang.authlib.yggdrasil.YggdrasilUserAuthentication;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.multiplayer.GuiConnecting;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Session;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.Proxy;
import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class Util {
    private static final ArrayList<TimeScheduledPacket> packetQueue = new ArrayList();
    public static Minecraft mc = Minecraft.mc();
    private static final AtomicInteger CONNECTION_ID = new AtomicInteger(0);
    private static final Logger logger = LogManager.getLogger();
    private NetworkManager networkManager;

    public static double eval(String str) {
        return new Object() {
            final int pos = -1;
            int c;

            void eatSpace() {
                while (Character.isWhitespace(this.c)) {

                }
            }

            double parse() {

                double v = parseExpression();
                if (this.c != -1)
                    throw new RuntimeException("Unexpected: " + (char) this.c);
                return v;
            }

            double parseExpression() {
                double v = parseTerm();
                while (true) {
                    eatSpace();
                    if (this.c == 43) {
                        //
                        v += parseTerm();
                    } else {
                        if (this.c != 45)
                            break;
                        //
                        v -= parseTerm();
                    }
                }
                return v;
            }

            double parseTerm() {
                double v = parseFactor();
                while (true) {
                    eatSpace();
                    if (this.c == 47) {
                        //
                        v /= parseFactor();
                    } else {
                        if ((this.c != 42) && (this.c != 40))
                            break;
                        if (this.c == 42) //
                            v *= parseFactor();
                    }
                }
                return v;
            }

            double parseFactor() {
                boolean negate = false;
                eatSpace();
                double v = 0;
                if (this.c == 40) {

                    double v1 = parseExpression();
                    if (this.c == 41) {

                    }
                } else {
                    if ((this.c == 43) || (this.c == 45)) {
                        negate = this.c == 45;

                        eatSpace();
                    }
                    StringBuilder sb = new StringBuilder();
                    while (((this.c >= 48) && (this.c <= 57)) || (this.c == 46)) {
                        sb.append((char) this.c);

                    }
                    if (sb.length() == 0)
                        throw new RuntimeException("Unexpected: " + (char) this.c);
                    v = Double.parseDouble(sb.toString());
                }
                eatSpace();
                if (this.c == 94) {

                    v = Math.pow(v, parseFactor());
                }
                if (negate)
                    v = -v;
                return v;
            }
        }.parse();
    }

    protected int charAt(int pos) {
        // TODO Auto-generated method stub
        return 0;
    }

    protected int length() {
        // TODO Auto-generated method stub
        return 0;
    }

    public static void sendPacketWithDelay(Packet p, long delayInMilliseconds) {
        packetQueue.add(new TimeScheduledPacket(p, delayInMilliseconds));
    }

    public static void damagePlayer(int amount) {
        for (int i = 0; i < 4; i++) {
            Minecraft.mc().thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(
                    Minecraft.mc().thePlayer.posX, Minecraft.mc().thePlayer.posY + 1.01D,
                    Minecraft.mc().thePlayer.posZ, false));

            Minecraft.mc().thePlayer.sendQueue
                    .addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(Minecraft.mc().thePlayer.posX,
                            Minecraft.mc().thePlayer.posY, Minecraft.mc().thePlayer.posZ, false));
        }

        Minecraft.mc().thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(
                Minecraft.mc().thePlayer.posX, Minecraft.mc().thePlayer.posY + 0.4D,
                Minecraft.mc().thePlayer.posZ, false));
    }

    public static Integer getRandomColor() {
        Random rnd = new Random();
        int intVal = rnd.nextInt(16777216);
        return Integer.valueOf(intVal);
    }

    public static int getDistanceToGround() {
        int i2 = -1;
        Minecraft mc = Minecraft.mc();
        for (int dist = 0; dist < 256; dist++) {
            BlockPos bpos = new BlockPos(mc.thePlayer.posX, mc.thePlayer.posY - dist, mc.thePlayer.posZ);
            Block block = mc.theWorld.getBlockState(bpos).getBlock();
            if (!(block instanceof BlockAir)) {
                i2 = dist;
            }
        }

        return i2;
    }

    public static int getNextStandableBlockHeight() {
        Minecraft mc = Minecraft.mc();
        int y = (int) mc.thePlayer.posY;
        int dist = 0;
        int result = -1;
        while (result == -1) {
            BlockPos bpos = new BlockPos(mc.thePlayer.posX, mc.thePlayer.posY - dist, mc.thePlayer.posZ);
            Block block = mc.theWorld.getBlockState(bpos).getBlock();
            if (!(block instanceof BlockAir)) {
                result = y - dist;
                return result;
            }
            dist++;
        }
        return -1;
    }

    public static boolean login(String username, String password) {
        YggdrasilUserAuthentication auth = new YggdrasilUserAuthentication(new YggdrasilAuthenticationService(Proxy.NO_PROXY, UUID.randomUUID().toString()), Agent.MINECRAFT);
        auth.setUsername(username);
        auth.setPassword(password);
        try {
            auth.logIn();
            Minecraft.mc().session = new Session(auth.getSelectedProfile().getName(), auth.getSelectedProfile().getId().toString(), auth.getAuthenticatedToken(), "legacy");
            return true;
        } catch (AuthenticationException e) {
            if (((e instanceof UserMigratedException)) || ((e instanceof InvalidCredentialsException))) {
                Minecraft.mc().session = new Session(username, UUID.randomUUID().toString(), "-", "legacy");
                return false;
            }
            Minecraft.mc().session = new Session(username, UUID.randomUUID().toString(), "-", "legacy");
            sendInfo("Couldn't login, is mojang down?");
        }
        return true;
    }

    public static void sendInfo(String string) {

    }

    public static Double distance(double x1, double y1, double z1, double x2, double y2, double z2) {
        Double distance = null;
        distance = Double.valueOf(Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2) + (z1 - z2) * (z1 - z2)));
        return distance;
    }

    public static Block getBlock(double posX, double posY, double posZ) {
        BlockPos bpos = new BlockPos(posX, posY, posZ);
        Block block = Wrapper.mc.theWorld.getBlockState(bpos).getBlock();
        return block;
    }

    public static void connect(String ip, int port) {
        GuiConnecting gui = new GuiConnecting(null, mc, ip, port);
        mc.currentScreen = gui;
    }

    public static boolean isInsideBlock() {
        for (int x = MathHelper.floor_double(mc.thePlayer.boundingBox.minX); x < MathHelper
                .floor_double(mc.thePlayer.boundingBox.maxX) + 1; x++) {
            for (int y = MathHelper.floor_double(mc.thePlayer.boundingBox.minY); y < MathHelper
                    .floor_double(mc.thePlayer.boundingBox.maxY) + 1; y++)
                for (int z = MathHelper.floor_double(mc.thePlayer.boundingBox.minZ); z < MathHelper
                        .floor_double(mc.thePlayer.boundingBox.maxZ) + 1; z++) {
                    Block block = Minecraft.mc().theWorld.getBlockState(new BlockPos(x, y, z)).getBlock();
                    if ((block != null) && (!(block instanceof BlockAir))) {
                        AxisAlignedBB boundingBox = block.getCollisionBoundingBox(mc.theWorld, new BlockPos(x, y, z),
                                mc.theWorld.getBlockState(new BlockPos(x, y, z)));
                        if ((boundingBox != null) && (mc.thePlayer.boundingBox.intersectsWith(boundingBox)))
                            return true;
                    }
                }
        }
        return false;
    }

    public static EntityPlayerSP getPlayer() {
        return Minecraft.mc().thePlayer;
    }

    private static class TimeScheduledPacket {
        private final Packet thePacket;
        private final long theDelay;

        public TimeScheduledPacket(Packet packet, long delay) {
            this.thePacket = packet;
            this.theDelay = delay;
        }

        public Packet getPacket() {
            return this.thePacket;
        }

    }
}