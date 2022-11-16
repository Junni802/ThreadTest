package com.example.threadtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.threadtest.databinding.ActivityMainBinding
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
	val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(binding.root)

		binding.btn1.setOnClickListener {
			var t1 = ThreadInheri()
			// Thread클래스를 상속받은 ThreadInheri의 인스턴스로 t1이라는 서브쓰레드가 생성됨
			t1.start()
			// start() : 쓰레드의 시작으로 자동으로 쓰레드 객체가 가진 run() 함수 호출
		}
		binding.btn2.setOnClickListener {
			var t2 = Thread(ThreadRunnable())
			t2.start()
		}
		
		binding.btn3.setOnClickListener { 
		// Runnable 인터페이스를 이용한 방법은 람다식으로 작업할 수도 있음
			Thread {
			// 하나의 함수만 사용할 경우 람다식으로 작업
				for(i in 1..3){
					Log.d("threadInfo", "threadLamda : ${i}")
				}
			}.start()
		}

		binding.btn4.setOnClickListener {
		// 코틀린에서 제공하는 thread() 함수 사용
			thread(start = true){
				for(i in 1..3){
					Log.d("threadInfo", "thread thread() : ${i}")
				}
			}
		}
	}
}

class ThreadInheri: Thread(){
// Thread 클래스를 상속방아 서브 쓰레드 기능 생성
	override fun run() {
		var num = 1
		while (num < 4){
			Log.d("threadInfo", "ThreadInheri : ${num}")
			if(num == 2) {
				sleep(4000)
			}
			num ++
		}
	}
}

class ThreadRunnable: Runnable {
// Thread 클래스를 상속받기 어려운 상황이면 Runnable 인터페이스를 구현하여 서브 쓰레드 생성 기능
// 단, 인터페이스이므로 반드시 run()함수를 오버라이딩 하여 구현해야 함
	override fun run() {
		var num = 3
		while (num > 0){
			if(num == 2) {
				Thread.sleep(4000)
			}
			Log.d("threadInfo" +
					"", "ThreadInheri : ${num}")
			num --
		}
	}
}