package com.kye.lottoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var controllerFlag: Boolean = true  // 진행 상태
    val result_num = arrayListOf<Int>() // 결과 번호 배열

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // TextView 객체 배열
        val lottNoArr = arrayListOf(no1, no2, no3, no4, no5, no6)  // 숫자공 정의 TextView
        var idx = 0  // 개별 추첨 시 각각의 열을 구분
        msg.text = ""  // 메시지 TextView 초기화

        /***************************
        * 개별번호추첨 버튼을 클릭 시
        * **************************/
        button.setOnClickListener {

            if (controllerFlag) { // 번호 추출 중이 아닐때만 수행 (true)

                controllerFlag = false // 추첨 진행 중
                lottAnimationView.playAnimation()

                if (idx < 6) {
                    displayCount(idx)
                    idx++
                }
            }
        }

        /***************************
         * 일괄번호추첨 버튼 클릭 시
         * **************************/
        button2.setOnClickListener {

            if (controllerFlag) { // 번호 추출 중이 아닐때만 수행 (true)
                idx = 0
                controllerFlag = false  // 추첨 진행 중
                lottAnimationView.playAnimation()
                displayAllCount()
            }
        }

        /***************************
         * 재시작 버튼 클릭 시
         * **************************/
        button3.setOnClickListener {

            // 모든 구분자 초기화
            idx = 0
            controllerFlag = true  // 추첨 가능

            lottNoArr.forEach {
                it.setBackgroundResource(R.drawable.lott_ball)
                it.text = "7"
            }

            result_num.clear() // 값을 초기화 한다.
            msg.text = " 모두 초기화 되었습니다. \n 다시 추첨할 수 있습니다. !!!"
        }

        /***************************
         * 저장 버튼 클릭 시
         * **************************/


        /***************************
         * 조회 버튼 클릭 시
         * **************************/

        /***************************
         * 기존 당첨 번호 비교
         * 기존에 몇등에 당첨되었었는지 알려 준다.
         * **************************/


    }

    /***************************
     * 개별번호추첨 버튼을 클릭 시 호출
     * **************************/
    fun displayCount(idx: Int) {

        val lottNoArr = arrayListOf(no1, no2, no3, no4, no5, no6) // TextView 객체 배열

        // Timer를 위한 설정 (익명 클래스로 생성해서 사용)
        val countTimer = object : CountDownTimer(2000, 100) {

            // 3초 중 0.5초마다 숫자를 화면에 순서대로 보여주는 척 한다.
            override fun onTick(millisUntilFinished: Long) {

                controllerFlag = false  // 상태 : 추첨 중
                msg.text = " ${idx + 1}번째 번호를 추첨 중입니다. \n 끝날 때 까지 기다려 주세요"
                var imsiArr = IntArray(6)

                for (i in 0..5){

                    while (true) {

                        val randomNumber = (Math.random() * 45 + 1).toInt()

                        // 최종 결과 배열의 수와 중복 비교
                        if (!result_num.contains(randomNumber)) {
                            imsiArr[i] = randomNumber
                            break;
                        }
                    }

                    lottNoArr[idx].text  = "${imsiArr[i]}"
                }
            }

            // 타이머가 끝나면 호출
            override fun onFinish() {

                // 추첨 결과 숫자를 결과값 배열에 넣는다.
                result_num.add(lottNoArr[idx].text.toString().toInt())

                // 번호에 따른 볼의 색을 지정
                when (lottNoArr[idx].text.toString().toInt()) {
                    1, 2, 3, 4, 5, 6, 7, 8, 9, 10 -> lottNoArr[idx].setBackgroundResource(R.drawable.lott_ball_1_10)
                    11, 12, 13, 14, 15, 16, 17, 18, 19, 20 -> lottNoArr[idx].setBackgroundResource(R.drawable.lott_ball_11_20)
                    21, 22, 23, 24, 25, 26, 27, 28, 29, 30 -> lottNoArr[idx].setBackgroundResource(R.drawable.lott_ball_21_30)
                    31, 32, 33, 34, 35, 36, 37, 38, 39, 40 -> lottNoArr[idx].setBackgroundResource(R.drawable.lott_ball_31_40)
                    41, 42, 43, 44, 45 -> lottNoArr[idx].setBackgroundResource(R.drawable.lott_ball_41_45)
                }

                lottAnimationView.cancelAnimation()

                if (idx == 5) {
                    msg.text = "번호 추첨이 모두 끝났습니다. \n 다시 시작하시려면 재시작 버튼을 터치해 주세요"
                } else {
                    msg.text = " ${idx + 1}번째 번호 추첨이 끝났습니다. \n 다시 개별번호추첨 버튼을 터치해 주세요"
                    controllerFlag = true
                }
            }
        }

        countTimer.start()
    }

    /***************************
     * 일괄추첨 버튼을 클릭 시 호출
     * **************************/
    fun displayAllCount() {

        // TextView 객체 배열
        val lottNoArr = arrayListOf(no1, no2, no3, no4, no5, no6)

        // Timer를 위한 설정 (익명 클래스로 생성해서 사용)
        val countTimer = object : CountDownTimer(3000, 100) {

            // 3초 중 0.5초마다 수행
            override fun onTick(millisUntilFinished: Long) {

                controllerFlag = false  // 추첨 중
                var imsiArr = IntArray(6)

                lottNoArr.forEachIndexed { index, textView ->

                    while (true) {
                        val randomNumber = (Math.random() * 45 + 1).toInt()
                        if (!imsiArr.contains(randomNumber)) {  // 번호 중복 체크
                            imsiArr[index] = randomNumber
                            break;
                        }
                    }

                    textView.text = "${imsiArr[index]}"
                }

                msg.text = "일괄 번호 추첨이 진행중입니다. \n 잠시만 기다려 주세요 "
            }

            // 타이머가 끝나면 호출
            override fun onFinish() {

                lottNoArr.forEachIndexed { index, textView ->

                    // 번호에 따른 볼의 색을 지정
                    when (lottNoArr[index].text.toString().toInt()) {
                        1, 2, 3, 4, 5, 6, 7, 8, 9, 10 -> textView.setBackgroundResource(R.drawable.lott_ball_1_10)
                        11, 12, 13, 14, 15, 16, 17, 18, 19, 20 -> textView.setBackgroundResource(R.drawable.lott_ball_11_20)
                        21, 22, 23, 24, 25, 26, 27, 28, 29, 30 -> textView.setBackgroundResource(R.drawable.lott_ball_21_30)
                        31, 32, 33, 34, 35, 36, 37, 38, 39, 40 -> textView.setBackgroundResource(R.drawable.lott_ball_31_40)
                        41, 42, 43, 44, 45 -> textView.setBackgroundResource(R.drawable.lott_ball_41_45)
                    }
                }

                msg.text = "일괄 번호 추첨이 모두 끝났습니다. \n 다시 시작하시려면 재시작 버튼을 터치해 주세요"
                lottAnimationView.cancelAnimation()
            }
        }

        countTimer.start()
    }

}
