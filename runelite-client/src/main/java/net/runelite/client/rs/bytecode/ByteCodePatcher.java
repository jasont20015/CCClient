package net.runelite.client.rs.bytecode;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.NotFoundException;
import net.runelite.client.RuneLite;
import net.runelite.client.rs.ClientLoader;
import net.runelite.client.rs.bytecode.transformers.ActorTransform;
import net.runelite.client.rs.bytecode.transformers.ProjectileTransform;
import net.runelite.client.rs.bytecode.transformers.getProjectileTransform;
import net.runelite.http.api.RuneLiteAPI;
import org.xeustechnologies.jcl.JarClassLoader;

import java.io.*;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

public class ByteCodePatcher {

    public static List<CtClass> modifiedClasses = new ArrayList<>();
    public static Hooks hooks = new Hooks();
    public static String clientInstance;
    public static JarClassLoader jcl = new JarClassLoader();
    public static ClassPool classPool = null;
    public static ClassLoader cl = ClassLoader.getSystemClassLoader();

    public static void applyHooks(File jf, Hooks hooks) {
        try {
            URLClassLoader child = new URLClassLoader(
                    new URL[] {jf.toURI().toURL()},
                    cl
            );
            try {
                Class actorClass = Class.forName(hooks.actorClass, false, child);
                transformActor(actorClass);
                Class projectileClass = Class.forName(hooks.projectileClass, false, child);
                transformProjectile(projectileClass);
                Class getProjectileClass = Class.forName(hooks.mainClientInstance, false, child);
                transformGetProjectile(getProjectileClass);

                ByteCodeUtils.updateHijackedJar();
            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void findHooks(String jf) {
        try {
            classPool = new ClassPool(true);
            classPool.appendClassPath(RuneLite.RUNELITE_DIR+"/injectedClient-"+ RuneLiteAPI.getVersion() +"-.jar");
        } catch (NotFoundException e) {
            e.printStackTrace();
        }

        try {
            jcl.add(new FileInputStream(jf));
            try (JarInputStream in = new JarInputStream(new BufferedInputStream(new FileInputStream(jf)))) {
                JarEntry entry;
                while ((entry = in.getNextJarEntry()) != null) {
                    if (entry.getName().endsWith(".class")) {
                        checkClasses(new File(jf), entry);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try {
            Writer writer = new FileWriter(ClientLoader.hooksFile);
            gson.toJson(hooks, writer);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ByteCodeUtils.updateHijackedJar();
    }

    public static void checkClasses(File jf, JarEntry entry) {
        try {
            URLClassLoader child = new URLClassLoader(
                    new URL[] {jf.toURI().toURL()},
                    cl
            );
            try {
                Class classToLoad = Class.forName(entry.getName().replace(".class", ""), false, child);
                checkActor(classToLoad);
                checkProjectile(classToLoad);
                checkgetProjectiles(classToLoad);
            } catch (Exception e) {
            e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Class not found: "+entry.getName());
        }
    }

    public static void checkActor(Class current) {
        try {
            Method method = current.getDeclaredMethod("setCombatInfo", new Class[] { int.class, int.class, int.class, int.class, int.class, int.class });
            if (method!=null) {
                hooks.actorClass = current.getName();
                System.out.println("[CCClient] Transforming Actor at class: "+current.getName());
                ActorTransform at = new ActorTransform();
                at.modify(current);
            }
        } catch (NoSuchMethodException e) {
            //e.printStackTrace();
        } catch (NoClassDefFoundError e) {
            //e.printStackTrace();
        }
    }

    public static void transformActor(Class current) {
        System.out.println("[Z-lyte] Transforming Actor at class: "+current.getName());
        ActorTransform at = new ActorTransform();
        at.modify(current);
    }

    public static void checkProjectile(Class current) {
        try {
            Method method = current.getDeclaredMethod("projectileMoved", new Class[] { int.class, int.class, int.class, int.class});
            if (method!=null) {
                hooks.projectileClass = current.getName();
                System.out.println("[CCClient] Transforming Projectile at class: "+current.getName());
                ProjectileTransform pt = new ProjectileTransform();
                pt.modify(current);
            }
        } catch (NoSuchMethodException e) {
            //e.printStackTrace();
        } catch (NoClassDefFoundError e) {
            //e.printStackTrace();
        }
    }

    public static void transformProjectile(Class current) {
        System.out.println("[CCClient] Transforming Projectile at class: "+current.getName());
        ProjectileTransform pt = new ProjectileTransform();
        pt.modify(current);
    }

    public static void transformGetProjectile(Class current) {
        System.out.println("[CCClient] Transforming getProjectile at class: " + current.getName());
        getProjectileTransform gpt = new getProjectileTransform();
        gpt.modify(current);
    }

    public static void checkgetProjectiles(Class current) {
        try {
            Method method = current.getDeclaredMethod("getProjectiles", new Class[]{});
            if (method != null) {
                hooks.mainClientInstance = current.getName();
                System.out.println("[CCClient] Transforming Projectile at class: " + current.getName());
                getProjectileTransform gpt = new getProjectileTransform();
                gpt.modify(current);
            }
        } catch (NoSuchMethodException e) {
            //e.printStackTrace();
        } catch (NoClassDefFoundError e) {
            //e.printStackTrace();
        }
    }

}
