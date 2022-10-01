# berke-alemdaroglu-week-6
## Coroutines

Sunucudan gelen veriyi dispatchers.io ile çağırdığımızda veriler main thread i bloklamadan, arka planda yapılmaktadır. 
Bu sayede main Thread bloklanmadan verilerin gelmesini sağlayabiliriz.
Lakin dispatcher io da bir sonsuz bir düngü kurarsak uygulama açılacaktır, kullanıcılar açılacak ilk sayfayı görecektir ama herhangi bir etkileşime giremezler.
Sonsuz döngüyü coroutine dışına alırsak uygulama hiç açılmayacaktır. Kullanıcı Sürekli beyaz ekranda bekleyecektir. 
Coroutine içine eklersek main de bulunan ui tasarımlarını kullanıcı görecektir ama etkileşime geçemeyecektir.
