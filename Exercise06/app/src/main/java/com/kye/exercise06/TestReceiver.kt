package com.kye.exercise06

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.provider.Telephony
import android.telephony.SmsMessage
import android.widget.Toast

class TestReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {

        //하나의 리시버로 여러 사건을 처리하려면 AndroidManifest.xml에 정의된 intent-filter명으로 구분
        when(intent.action){
            //"android.intent.action.BOOT_COMPLETED" -> { //재부팅인 경우
            Intent.ACTION_BOOT_COMPLETED -> {  //intent의 상수값을 사용해도 됨
                //토스트로 간단하게 찍어본다.
                var t1 = Toast.makeText(context, "부팅 완료", Toast.LENGTH_SHORT)
                t1.show()
            }
            //"android.provider.Telephony.SMS_RECEIVED" -> { //문자 수신인 경우
            Telephony.Sms.Intents.SMS_RECEIVED_ACTION -> { //Telephony에 정의된 상수값을 사용해도 됨
                //문자열하고 발신자 전화번호를 읽는다.
                var str = ""
                var bundle = intent.extras
                if (bundle != null){
                    var obj = bundle.get("pdus") as Array<Any>
                    var msg = arrayOfNulls<SmsMessage>(obj.size)

                    //문자 메시지 추출 (단문메세지가 길어지면 짤려서 여러개로 올 수 있기에 루프)
                    for (i in obj.indices){
                        msg[i] = SmsMessage.createFromPdu(obj[i] as ByteArray)
                    }
                    //추출된 문자 메시지를 토스트를 찍어보기 위해 배열에서 꺼내서 문자열 변수에 담는다.
                    for (i in msg.indices){
                        //전화번호 + 문자
                        str = msg[i]?.originatingAddress +" : " + msg[i]?.messageBody
                        var t2 = Toast.makeText(context, str, Toast.LENGTH_SHORT)
                        t2.show()
                    }
                }
            }
        }
    }
}