package com.pineone.icbms.so.iot.servicerunner.tp;


public class ThreadContainer implements Runnable {
	
	private Executable mWork = null;
	private ThreadContainerListener mListener = null;
	private Object mMutex;
	private Thread mThread;
	private boolean isWork;

	public ThreadContainer(ThreadContainerListener listener) {
		isWork = true;
		mListener = listener;
		mMutex = new Object();
		mThread = new Thread(this);
		mThread.start();
	}
	
	public void setExecutable(Executable work) {
		mWork = work;
		synchronized (mMutex) {
			mMutex.notify();
		}
	}

	public void stop() {
		isWork = false;
		synchronized (mMutex) {
			mMutex.notify();
		}

		try {
			mThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
			mListener.OnFail(getId());
		}		
 	}

	public Long getId() {
		return mThread.getId();
	}
	
	@Override
	public void run() {
		while(isWork) {
			int ret = 0;
			try {
				
				synchronized (mMutex) {
					mListener.OnStandBy(getId());
					mMutex.wait();
				}
				if(null != mWork) {
					ret = mWork.execute();
					mWork = null;
					if(0 > ret)
						mListener.OnFail(getId());
					else
						mListener.OnComplete(getId());
				}
				mListener.OnComplete(getId());
			} catch (NoClassDefFoundError | Exception e) {
				e.printStackTrace();
				mListener.OnThreadCrash(getId());
			}
		}
	}
}
