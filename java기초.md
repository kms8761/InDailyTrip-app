
### 자바
에서는 클래스== 변수랑 함수도 다품을수있따.
PUBLIC  함수앞에 붙는경우
함수를 허가해주는 열어주기위한 PUBLIC 
STATIC  함수에 붙게되면 클래스를 생성안해도 이함수 호출가능하게 한다 (정적멤버함수)
메모리를 선언안해도 쓸수있게해준는거
MAIN은 벌쳐머신이 실행시킨다 벌처머신은 메인만 보기때문에


자바패키지안에 클래스
OBJECT 클래스의 최상위클래스
출력하기 위해선 SYSTEM
int nVar =12;
출력 system.out.println("정수 "+ nVar+"부동소수" )
print 한문자 
printf 다나와 
println 다나와 +한줄띄워줘


### 기본연산
for(초기화; 조건식; 증감)
for(int i=0; i<3;i++)
while(조건식)->무조건 true,false
c는 1넣으면 무한으로돈다 하지만 java는 true fasle만넣어야해
while(i<3)
{
system.out.pint
i++;
}
switch문
char cVar = 'a';

switch(cVar)
{
case 1:
                   system.out.print;
                  break;
case2;
}

###### 배열

배열
int Array[100]

b.언어적 특성
c언어와 c++언어
int nArray-일반 변
java nArray

배열선언
boolean->false값 기본으로 들어가있따.

int[] nArray;  ->일반변수 1차원배열스타일이라는것만 알수있다->참조형 배열변수

nArray = new int[10]; 객체       배열변수에서 객체로 되는 과정 new로 인해서

nArray = new int[5];

객체

객체안에 변수와 함수가 있다 함수는 변수의 값을 바꿔주기 위해서 

C 변수함수 분리 C++ 클래스라는 객체다 객체안에 변수함수 같이 포함되있는 함수는 객체안에서 이뤄지는 변수가 객체라는거안에 그런게있따
 
nArray. 하면 객체에 속하는 멤버함수 이용할수있다.

nArray = new int[3][];

nAray[0] = new int[2];

nArray[1]  new int[3];
초기화 

행과 열 단위로 초기화 하는 방법으로 c동일

int[][] nArray1 = {{ 1,2}, {3,4} }; 

///////////////언어적인 문법/////////////

##6강 클래스(자바만의 특별한 개념 개념적인 문법)
클래스(c의 구조체 확장형)
(c는 구조체 변수의 값만 선언)
+클래스 안에서는 변수+함수

class 클래스명(첫문자 대문자관례)

클래스안에 선언변수 멤버변수

-공용변수

-전역변수와 유사한 속성 - > 클래스 안에서만

-객체 속성은 곧 멤버변수이다.

클래스안에 선언함수 메소드

class book{

string m_strTtile;

int m_nPage;

}

(3) 메소드 형식

void setTtile(String strTitle)

{

m_strTtile = strTitle;

}

new를 사용하여 멤버변수와 메소드에 접근-이게바로 객체생성

클래스명  객체명 = new 클래스명();

new로 인하여 메모리생성 근데 메모리해제부분가 없었는데

가비지 컬렉터가 사용하지 않는 메모리 자동으로 해제해버려서 메모리 누수가 없다-자바기능  

메모리관련 부분에서는 예민할수밖에없는데 해결


### 접근제어자
private:  클래스 안에서만 접근가능,밖에서 호출 불가능
public:전체 다가능
protected 같은 클래스 패키지 하위클래스가능
friendly 생략된 제어자


퍼블릭안쪽부분이 외부  클래스 부분이 내부

메인도 클래스 안에사용하니까 가능하다

접근자 사용형식
PUBLIC IN M_nVar;

private int geVar()
{
return m_var;
}
오버로딩
중복되는 메소드명을 클래스안에서 정의할 수 있도록 하는 것

같은 함수명으로 매개변수에 따라서 이게 어떤걸 가질지 다양한 값을 받아 처리
-함수명 하나만 기억하면됨

@@@@@@@@@@@@@@@@@@8강@@@@@@@
#### 생성자-(함수이겠구만(
객체가 생성될때 한번 호출 되는 메소드 (NEW를 이용할때)
자동*으로 호출되는 메소드
리턴형 없고 클래스명과 함수명이 같다!!!!
public!!!반드시 퍼블릭으로 ㅏ져야해
void이런게 없을때

class MyClass
{
public MyClass()
{
}
public MyClass(int nVar1, int nVar2)
{
}

this
클래스변수 또는 키워드
this.멤버면수
this.메소드() 
내부에서 호출한거 당연히 객체가 생성이 됐어야해 !!! new 로  
this()
생성자안에서 또다른 객체를 생성할때 


static(선언만으로도 객체선언안해줘도 아무때나 사용할수있다.
c에서는 function static it k =0으로 초기화되고 함수가 호출되었을때 메모리에 유지
변수에만 국한 변수의값이 유지되는
java
static 데잍터형 멤버변수 
static 데잍터형 메소드 () -> 메인에서 봄
static 붙으면 객체 생성안해도 생성이전의 호출해서 쓸수있는 키워드
 A Test=new A()안해도 A.k로 쓸쑤이다
ex)String 객체 생성안해도 쓰자나
main() 클래스안에 메인 포함
실제 jvm에서 메인을 직접쓸수있다.
main 앞에 static있다.

static 멤버변수 특징
멤버변수는 생성되는   모든 객체에서 전역변수로 사용가능
객체 생성 이전 호출 함수가능한게 static 객체 생성이전에 사용가능.
static 메소드는 같은 클래스안에 static 메소드만을 호출할 수 있다.
thi를 사용할수없다 객체를 생성이전에 만드는게static이라
오버라이딩 할수없다
