package com.zodiac.polit.util;

/**
 * Description: 倒计时工具类
 * Author: 王鹏飞
 * Email: 152919671792163.com
 * Date: 2017-08-01 18:13
 */
public class CountDownTimerTask extends CountDownTimer
{
	private OnTimerCallback onTimerCallback;


	public static final long UNIT_TIME = 1000;

	public CountDownTimerTask(OnTimerCallback onTimerCallback)
	{
		super(60*UNIT_TIME, UNIT_TIME);
		this.onTimerCallback = onTimerCallback;
	}

	public CountDownTimerTask(OnTimerCallback onTimerCallback, long limitTime)
	{
		super(limitTime*UNIT_TIME, UNIT_TIME);
		this.onTimerCallback = onTimerCallback;
	}


	public CountDownTimerTask(OnTimerCallback onTimerCallback, long limitTime , long unitTime)
	{
		super(limitTime, unitTime);
		this.onTimerCallback = onTimerCallback;
	}

	/**
	 * 判断当前倒计时任务是否结束
	 * @return
	 */
	public boolean isFinish()
	{
		return isFinish;
	}
	@Override
	public void onTick(long millisUntilFinished)
	{
		isFinish = false;
		if (onTimerCallback != null)
			onTimerCallback.onTick(millisUntilFinished);
	}

	@Override
	public void onFinish()
	{
		isFinish = true;
		if (onTimerCallback != null)
			onTimerCallback.onFinish();
	}
	public interface OnTimerCallback{
		void onTick(long millisUntilFinished);
		void onFinish();
	}
}
