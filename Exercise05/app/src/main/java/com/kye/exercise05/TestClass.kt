package com.kye.exercise05

/*********************************************
 * Parcelable 인터페이스를 구현한 TestClass 만들기
 **********************************************/
import android.os.Parcel
import android.os.Parcelable

class TestClass() : Parcelable{

    var data10 = 0
    var data20:String? = null

    //intent에 담을 때 호출됨
    //Parcel타입의 객체가 parcel 파라미터로 넘어오는데 여기에는 복원하고자 하는 객체의 멤버값이 들어있음
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        //순서 중요 (Write 한 순서대로  읽어야 함)
        parcel.writeInt(data10)
        parcel.writeString(data20)
    }

    override fun describeContents(): Int {
        return 0
    }

    //CREATOR라는 static 타입의 변수 선언 및 정의
    companion object CREATOR : Parcelable.Creator<TestClass> {

        //객체 복원 메서드 (intent로 부터 객체 정보를 추출할 때 호출됨)
        override fun createFromParcel(parcel: Parcel): TestClass {
            //writeToParcel 메서드에서 데이터 셋 했던 순서대로 읽는다.
            val test = TestClass()
            test.data10 = parcel.readInt()
            test.data20 = parcel.readString()

            return test
        }

        //객체 하나가 아니라 배열에 담아서 넘어 오면 newArray 메서드가 호출된다.
        override fun newArray(size: Int): Array<TestClass?> {
            return arrayOfNulls<TestClass>(size)
        }
    }
}