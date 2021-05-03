package com.kye.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.custom_list.view.*

// 프로필을 넣을 데이터 클래스
class Data(val profile : Int, val name : String){

}

// RecyclerView.ViewHolder(v) 메서드에 파라미터를 넘겨주면서 상속 받는다.
// ViewHolder 는 많은 수의 리스트 아이템이 있을 때 한 화면에 표시되는 리스트 항목
// 여기서 파라미터 v는 커스텀리스트(custom_list.xml)를 뜻한다
class CustomViewHolder(v : View) : RecyclerView.ViewHolder(v){

    // 이미지를 수정할 때 사용
    val profile = v.imageView_custom
    val name = v.textView_custom

}

// 커스텀 리스트를 뷰로 만들어서 액티비티 메인에 붙여줘야 하기 때문에
// RecyclerView 의 어댑터를 상속받아서 커스텀 어댑터를 만들어야 한다.
class CustomAdapter(val context : Context) : RecyclerView.Adapter<CustomViewHolder>() {

    // profile작성을 위한 DataList
    val dataList = arrayListOf(
        Data(android.R.drawable.ic_lock_idle_lock, "0번 자물쇠 열림"),
        Data(android.R.drawable.ic_lock_lock, "1번 자물쇠 잠김"),
        Data(android.R.drawable.ic_lock_lock, "2번 자물쇠 잠김"),
        Data(android.R.drawable.ic_lock_lock, "3번 자물쇠 잠김"),
        Data(android.R.drawable.ic_lock_lock, "4번 자물쇠 잠김"),
        Data(android.R.drawable.ic_lock_idle_lock, "5번 자물쇠 열림"),
        Data(android.R.drawable.ic_lock_lock, "6번 자물쇠 잠김"),
        Data(android.R.drawable.ic_lock_idle_lock, "7번 자물쇠 열림"),
        Data(android.R.drawable.ic_lock_lock, "8번 자물쇠 잠김"),
        Data(android.R.drawable.ic_lock_lock, "9번 자물쇠 잠김"),
        Data(android.R.drawable.ic_lock_lock, "10번 자물쇠 잠김"),
        Data(android.R.drawable.ic_lock_lock, "11번 자물쇠 잠김")
    )
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {

        // custom_list.xml 를 뷰로 확장 즉, 만들기 위한 과정
        val cellForRow = LayoutInflater.from(context).inflate(R.layout.custom_list, parent, false)

        // custom_list.xml이 뷰로 된 후 이를 커  스텀 홀더에 넣어서 반환한다. (커스텀홀더가 화면에 나타남)
        return CustomViewHolder(cellForRow)
    }

    // 수정할 일이 있을때 나 클릭 이벤트를 처리할 때 사용 가능
    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {

        val curData = dataList[position]  // 현재 한 화면에 보여줄 데이터 row 배열

        // dataList의 이미지를 CustomViewHolder 속성인 profile로 할당
        // dataList의 name을 CustomViewHolder 속성인 name으로 할당하면 CustomViewHolder에 의해 화면에 보임
        holder.profile.setImageResource(curData.profile)
        holder.name.text = curData.name

        // 아이템을 클릭하면 토스트 메시지를 띄운다. (메인에서 할 수 있지만 여기가 편함)
        holder.itemView.setOnClickListener {
            Toast.makeText(context, curData.name, Toast.LENGTH_SHORT).show()
        }

    }

    // 뷰홀더의 갯수를 반환한다. (화면에 커스텀리스트가 데이터 갯수에 맞게 뜨도록 한다.)
    override fun getItemCount() = dataList.size
    //override fun getItemCount(): Int {}


}