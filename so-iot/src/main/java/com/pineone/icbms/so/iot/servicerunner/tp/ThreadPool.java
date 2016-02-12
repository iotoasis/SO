package com.pineone.icbms.so.iot.servicerunner.tp;


import java.util.ArrayList;
import java.util.HashMap;

public class ThreadPool {
	public static final long THREAD_DEFAULT_SIZE = 10;

	private static ThreadPool mInstance = null;
	
	private long mThreadMaxSize;
	private HashMap<Long, ThreadContainer> mThreadList = null;
	private ArrayList<Long> mAvailableIdList = null;
	private HashMap<Long, ThreadPoolListener> mThreadListener = null;
	private Object mMutex = null;
	
	public static ThreadPool getInstance() {
		if(null == mInstance)
			mInstance = new ThreadPool();
		return mInstance;
	}

	public ThreadPool() {
		mThreadMaxSize = THREAD_DEFAULT_SIZE;

		if(null == mThreadList)
			mThreadList = new HashMap<Long, ThreadContainer>();
		
		if(null == mAvailableIdList)
			mAvailableIdList = new ArrayList<Long>();
		
		if(null == mMutex)
			mMutex = new Object();
		
		if(null == mThreadListener)
			mThreadListener = new HashMap<Long, ThreadPoolListener>();
	}
	
	public void setThreadCount(int threadSize) {
		mThreadMaxSize = threadSize;
	}
	
	public void createThreadPool() {
		for(int i = 0; i < mThreadMaxSize; i++) {
			ThreadContainer tc = new ThreadContainer(mListener);
			long key = tc.getId();
			mThreadList.put(key, tc);
		}
	}
	
	public void destroy() {
		System.out.println("[ThreadPool][destroy start]");
		for(long key : mThreadList.keySet()) {
			ThreadContainer tc = mThreadList.get(key);
			tc.stop();
		}
		mThreadList.clear();
		clearAvailableThreadList();

		mThreadList = null;
		mAvailableIdList = null;
		System.out.println("[ThreadPool][destroy end]");
	}

	public long getThreadMaxSize() {
		return mThreadMaxSize;
	}

	public long setWork(Executable work) {
		long id = 0;
		if(0 < getAvailableThreadCount()) {
			id = setExecutable(work, null);
		}
		return id;
	}

	public long setWork(Executable work, ThreadPoolListener listener) {
		long id = 0;
		if(0 < getAvailableThreadCount()) {
			id = setExecutable(work, listener);
		}
		return id;
	}
	
	public long getAvailableThreadCount() {
		long size = 0;
		synchronized (mMutex) {
			size = mAvailableIdList.size();
		}
		System.out.println("[ThreadPool][getAvailableThreadCount][size:"+size+"]");
		return size;
	}

	private void clearAvailableThreadList() {
		synchronized (mMutex) {
			mAvailableIdList.clear();
		}
	}

	private long setExecutable(Executable work, ThreadPoolListener listener) {
		long id = 0;
		synchronized (mMutex) {
			id = mAvailableIdList.get(0);
			mAvailableIdList.remove(0);
			ThreadContainer tc = mThreadList.get(id);
			tc.setExecutable(work);
			mThreadListener.put(id, listener);
		}
		return id;
	}
	
	private ThreadContainerListener mListener = new ThreadContainerListener() {

		@Override
		public void OnStandBy(long id) {
			synchronized (mMutex) {
				mAvailableIdList.add(id);
			}
		}
		
		@Override
		public void OnFail(long id) {
			ThreadPoolListener listener = null;
			synchronized (mMutex) {
				listener = mThreadListener.get(id);
				mThreadListener.remove(id);
			}
			if(null != listener)
				listener.OnFail(id);
		}
		
		@Override
		public void OnComplete(long id) {
			ThreadPoolListener listener = null;
			synchronized (mMutex) {
				listener = mThreadListener.get(id);
				mThreadListener.remove(id);
			}
			if(null != listener)
				listener.OnComplete(id);
		}

		@Override
		public void OnThreadCrash(long id) {
			ThreadPoolListener listener = null;
			synchronized (mMutex) {
				listener = mThreadListener.get(id);
				mThreadListener.remove(id);
			}
			if(null != listener)
				listener.OnThreadCrash(id);
		}
	};
}
