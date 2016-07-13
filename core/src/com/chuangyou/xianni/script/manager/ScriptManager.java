package com.chuangyou.xianni.script.manager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.concurrent.ConcurrentHashMap;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import com.chuangyou.common.util.Config;
import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.script.IScript;
/**
 * 脚本管理器
 * @author laofan
 *
 */
public class ScriptManager
{
    
    private static ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
    
    private static long lastModified;
    
    private static String ROOT;
    
    // 存放对应脚本
    private static ConcurrentHashMap<String, IScript> scriptMap = new ConcurrentHashMap<String, IScript>();
    
 
    
    public static boolean init(){
    	ROOT = Config.getValue("script_path");
    	File dir = new File(ROOT);
        lastModified = dir.lastModified();
        loadFiles(dir);
        return true;
         
    }
    
    private ScriptManager()
    {
       
    }
    
    public static void reLoad()
    {
    	File dir = new File(ROOT);
    	lastModified =  dir.lastModified();
        loadFiles(dir);
    }
    
    // 遍历并加载所有脚本
    private static void loadFiles(File dir)
    {
        if (dir.isDirectory())
        {
            File[] files = dir.listFiles();
            for (File file : files)
            {
                loadFiles(file);
            }
        }
        else
        {
            loadScript(dir);
        }
    }
    
    /**
     * 注册脚本
     * 
     * @param script
     */
    private static void registerScript(IScript script)
    {
        scriptMap.put(script.getScriptId(), script);
    }
    
    /**
     * 加载脚本
     * 
     * @param path
     */
    public static void loadScript(File file)
    {
        try
        {
            engine.eval(new FileReader(file));
            
            Invocable invocable = (Invocable)engine;
            IScript script = invocable.getInterface(IScript.class);
            Log.info("script:"+script.getInterfaceName());
            // 根绝接口名获取对应接口并映射
            script = (IScript)invocable.getInterface(
            		
                Class.forName(script.getInterfaceName(), true, Thread.currentThread().getContextClassLoader()));
                
            // 注册脚本
            registerScript(script);
            
        }
        catch (FileNotFoundException | ScriptException e)
        {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }
    
    
    /**
     * 获取脚本
     * 
     * @param id
     * @return
     */
    public static IScript getScriptById(int id)
    {
        return getScriptById("" + id);
    }

    /**
     * 获取脚本
     * 
     * @param id
     * @return
     */
    public static IScript getScriptById(String id)
    {
        return scriptMap.get(id);
    }
    
    public static long getLastModified()
    {
        return lastModified;
    }

    
    
    
}
