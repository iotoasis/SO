package com.pineone.icbms.so.serviceprocessor.route;

import com.pineone.icbms.so.serviceutil.interfaces.processor.AGenericProcessor;
import com.pineone.icbms.so.serviceutil.interfaces.processor.IGenericProcessor;
import com.pineone.icbms.so.serviceprocessor.processor.context.ContextModelProcessor;
import com.pineone.icbms.so.serviceprocessor.processor.devicecontrol.DeviceControlProcessor;
import com.pineone.icbms.so.serviceprocessor.processor.orchestrationservice.OrchestrationServiceProcessor;
import com.pineone.icbms.so.serviceprocessor.processor.virtualobject.VirtualObjectProcessor;
import com.pineone.icbms.so.util.id.IdUtils;
import com.withwiz.plankton.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;


/**
 * 미사용
 * Process router class.<BR/>
 * This class is Singleton instance.<BR/>
 * <p>
 * Created by uni4love on 2016. 11. 24..
 */
@Component
public final class ProcessorRouter {
    /**
     * logger
     */
    private Logger log = LoggerFactory.getLogger(ProcessorRouter.class);

    /**
     * process line
     */
    private ProcessLine line = new ProcessLine();

    /**
     * serviceprocessor list, <BR/>
     *
     * TODO: 추후 class loader를 이용하여 동적으로 구동하도록 처리 요망.
     * TODO: 외부에서 inject할 수 있도록 함.<BR/>
     * TODO: filter class도 동일 라인에 추가 가능.<BR/>
     */
    static Class[] processorList = {
            ContextModelProcessor.class,
            OrchestrationServiceProcessor.class,
            VirtualObjectProcessor.class,
            DeviceControlProcessor.class
    };

    /**
     * singleton instance holder
     */
    private static final class SingletonHolder {
        static final ProcessorRouter singleton = new ProcessorRouter();
    }

    /**
     * constructor
     */
    private ProcessorRouter() {
        init();
    }

    /**
     * return ProcessorRunner singleton instance.<BR/>
     *
     * @return ProcessorRunner
     */
    public static ProcessorRouter getInstance() {
        return SingletonHolder.singleton;
    }

    /**
     * initialize<BR/>
     */
    private void init() {
        //instance objects
        reload();
    }

    /**
     * reload environments<BR/>
     */
    public void reload() {
        //load classes
        loadClass();
    }

    /**
     * load classes.<BR/>
     */
    private void loadClass() {
        //temporary load static classes;
        //serviceprocessor id is name.
        for (Class clz : processorList) {
            Class<?> cls = null;
            Class[] args = new Class[]{String.class, String.class};
            try {
                cls = Class.forName(clz.getName());
                Constructor constructor = cls.getConstructor(args);
                //parameters: id, name
                Object obj = constructor.newInstance(IdUtils.createRandomUUID(), cls.getName());
                addProcessor((IGenericProcessor) obj);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * add a serviceprocessor to registry.<BR/>
     *
     * @param processor serviceprocessor
     */
    public void addProcessor(IGenericProcessor processor) {
        this.line.addProcessor(processor);
    }

    /**
     * return a serviceprocessor from registry.<BR/>
     *
     * @param key key for finding a serviceprocessor.
     * @return IGenericProcessor
     */
    public IGenericProcessor getProcessor(String key) {
        return this.line.getProcessor(key);
    }

    /**
     * return existence of serviceprocessor<BR/>
     *
     * @param processorName name
     */
    public boolean existProcess(String processorName) {
        return line.existProcessor(processorName);
    }

    /**
     * print USAGE.<BR/>
     */
    public static void printUsage() {
        StringBuilder sb = new StringBuilder("# Usage) java -jar so-serviceprocessor.jar -P[processor1,processor2,..]\n");
        sb.append("\n= current serviceprocessor name list:\n");
        for (Class cls : processorList) {
            sb.append("+- " + cls.getName() + "\n");
        }
        //System.out.println(sb);
    }

    /**
     * run processors.<BR/>
     *
     * @param args runnable serviceprocessor name
     */
    public void run(String[] args) {
//        log.info("user.dir: {}", System.getProperty("user.dir"));
        log.debug("arguments[{}]: {}", args.length, Arrays.stream(args).toArray());
        // serviceprocessor name list from arguments
        ArrayList<String> processorNameListFromArguments = getProcessorNameListFromArguments(args);
        //
        if (processorNameListFromArguments == null || !checkExistProcessorNameList(processorNameListFromArguments)) {
            //usage
            ProcessorRouter.printUsage();
        } else {
            ProcessorRouter processRouter = ProcessorRouter.getInstance();
            for (String processorName : processorNameListFromArguments) {
                AGenericProcessor processor = (AGenericProcessor)processRouter.getProcessor(processorName);
                new Thread(processor).start();
            }
        }
    }

    /**
     * run parameter("serviceprocessor class name") list.<BR/>
     *
     * @param processorNameList serviceprocessor name list
     * @return result after checking
     */
    private boolean checkExistProcessorNameList(ArrayList<String> processorNameList) {
        //check serviceprocessor class
        ProcessorRouter processRouter = ProcessorRouter.getInstance();
        for (String processorName : processorNameList) {
            log.info("Your serviceprocessor name: {}", processorName);
            if (!processRouter.existProcess(processorName)) {
                log.error("Your serviceprocessor NOT exist: {}", processorName);
                return false;
            }
        }
        return true;
    }

    /**
     * return serviceprocessor name list from arguments.<BR/>
     *
     * @param args arguments
     * @return serviceprocessor name list
     */
    private ArrayList<String> getProcessorNameListFromArguments(String[] args) {
        ArrayList<String> processorNameList = null;
        for(String arg: args) {
            if (arg.startsWith("-P")) {
                if(processorNameList == null)
                    processorNameList = new ArrayList<>();
                String filteredArgs = StringUtil.getRight(arg, "-P");
                log.debug("filteredArgs: {}", filteredArgs);
                String[] processorArray = filteredArgs.split(",");
                processorNameList.addAll(Arrays.asList(processorArray));
                log.debug("current processorList: {}", processorNameList);
            }
        }
        return processorNameList;
    }


    /**
     * runner test main method.<BR/>
     * <p>
     * usage) java -j [jar file] [serviceprocessor name]
     *
     * @param args serviceprocessor name
     */
//    public static void main(String[] args) {
//        ProcessorRouter.getInstance().run(args);
//    }
}