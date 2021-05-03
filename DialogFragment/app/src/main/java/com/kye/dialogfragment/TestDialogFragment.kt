package com.kye.dialogfragment

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.DialogFragment


/**
 * A simple [Fragment] subclass.
 * Use the [TestDialogFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TestDialogFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        var builder = AlertDialog.Builder(activity)
        builder.setTitle("타이틀 입니다.")
        builder.setMessage("메시지 입니다")

        // 다이얼로그 리스너 객체 생성
        var listener = DialogListener()

        // 위에서 생성한 리스너를 넘겨서 버튼 작동 시 반응하게 한다.
        builder.setPositiveButton("positive", listener)
        builder.setNeutralButton("neutral", listener)
        builder.setNegativeButton("negative", listener)

        var alert = builder.create()  // 빌더 생성해서 변수에 할당

        return alert
    }

    // 다이얼로그 리스너 정의
    inner class DialogListener : DialogInterface.OnClickListener {
        override fun onClick(dialog: DialogInterface?, which: Int) {
            // MainActivity의 참조값 가져오기
            var main_activity = activity as MainActivity
            when (which){
                DialogInterface.BUTTON_POSITIVE -> {
                    // MainActivity의 testView에 할당하여 보여주기
                    main_activity.binding.textView.text = "positive"
                }
                DialogInterface.BUTTON_NEUTRAL -> {
                    main_activity.binding.textView.text = "neutral"
                }
                DialogInterface.BUTTON_NEGATIVE -> {
                    main_activity.binding.textView.text = "negative"
                }
            }

        }
    }

}