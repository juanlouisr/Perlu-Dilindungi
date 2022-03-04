# IF3210-2022-Android-20

## Deskripsi
Aplikasi Perlu Dilindungi adalah aplikasi berbasis Android yang dibangun menggunakan bahasa Kotlin. Aplikasi ini merupakan aplikasi kesehatan yang bertujuan untuk memberikan info kesehatan kepada masyarakat khususnya di masa pandemi ini. Info tersebut berupa berita-berita terkait COVID dan daftar fasilitas kesehatan yang ada di Indonesia. Selain hal-hal tersebut, aplikasi ini juga memiliki fitur checkin yang mana penggunanya bisa masuk ke suatu tempat dengan scan QR Code yang ada.

## Cara Kerja
Aplikasi Perlu Dilindungi yang kami buat terdiri dari 5 fitur utama, antara lain:
1. Menampilkan Berita COVID-19 <br />
Untuk melihat daftar berita COVID-19, Anda dapat menekan tombol "Berita" yang ada pada bottom navigation.<br />
Kemudian untuk melihat detail dari daftar berita yang ditampilkan, Anda hanya perlu menekan salah satu item pada daftar tersebut. Lalu akan tampil detail berita dari item yang Anda tekan dalam bentuk webview.<br />

2. Menampilkan Daftar Faskes untuk Vaksinasi <br />
Untuk melakukan pencarian faskes, Anda dapat menekan tombol "Faskes" yang ada pada bottom navigation.<br />
Pada halaman pencarian faskes ini, Anda perlu memilih provinsi dan kabupaten/kota lokasi faskes yang ingin dicari terlebih dahulu. Setelah itu, Anda dapat menekan tombol "Search" yang akan menampilkan maksimal lima daftar faskes terdekat berdasarkan lokasi yang Anda masukkan.<br />
Halaman daftar faskes ini memiliki layout yang responsive sehingga dapat ditampilan secara potrait maupun landscape.<br />

3. Menampilkan Detail Informasi Faskes <br />
Untuk melihat detail informasi dari faskes yang telah ditampilkan pada daftar hasil pencarian faskes, Anda hanya perlu menekan salah satu item pada daftar. Lalu akan tampil detail faskes dari item yang Anda tekan.<br />
Anda dapat melakukan bookmark untuk item faskes tersebut dengan menekan tombol "+BOOKMARK" ataupun melakukan un-bookmark dengan menekan tombol "-BOOKMARK" yang ada pada halaman detail faskes.<br />
Anda juga dapat membuka Google Maps untuk melihat lokasi faskes tersebut dengan menekan tombol "GOOGLE MAPS".<br />

4. Menampilkan Daftar Bookmark Faskes <br />
Untuk melihat daftar faskes yang sudah Anda bookmark, Anda dapat menekan tombol "Bookmark" yang ada pada bottom navigation.<br />
Untuk melihat detail dari faskes yang berada pada daftar bookmark ini, Anda hanya perlu menekan salah satu item yang ada pada daftar tersebut. Lalu akan tampil detail faskes dari item yang Anda tekan.<br />
Untuk menghapus suatu item faskes pada daftar bookmark faskes, Anda pada melakukan un-bookmark dengan menekan tombol "-BOOKMARK" yang ada pada halaman detail faskes.<br />

5. Melakukan "Check-In" <br />
Untuk melakukan check-in, Anda perlu masuk ke halaman QR Code Scanner dengan menekan floating button yang berapa pada kanan bawah layar.<br />
Pada halaman QR Code Scanner akan ditampilkan informasi suhu kamar pada kanan atas layar.<br />
Untuk melakukan scanning, Anda perlu menekan icon yang ada pada tengah layar. Lalu aplikasi akan meminta izin untuk mengakses kamera android Anda. Setelah akses diberikan, Anda hanya perlu mengarahkan kamera Anda pada QR Code yang Anda miliki. Ketika proses scanning berhasil, status Anda akan ditampilkan berdasarkan pembacaan QR Code yang telah dilakukan. Akan ditampilkan alasan jika status Anda red atau black. <br />

## Library
1. Retrofit (Mengambil data dari API)
2. Room (Penyimpanan SQLite secara lokal untuk Bookmark)
3. Zxing (Scan QR Code)
4. Google Material.io (Material Design)
5. Glide (Image Loader pada Daftar Berita)

## Screenshot


    Halaman Landing Page (Splash Screen)

<img src="screenshot/splashscreen.png" alt="drawing" width="200"/>

    Halaman News

<p float="left">
<img src="screenshot/news.png" alt="drawing" width="200"/>
<img src="screenshot/news_detail.png" alt="drawing" width="200"/>
</p>

    Halaman Faskes

<p float="left">
<img src="screenshot/faskes.png" alt="drawing" width="200"/>
<img src="screenshot/faskes_detail.png" alt="drawing" width="200"/>
</p>

<img src="screenshot/faskes_land.png" alt="drawing" width="400"/>


    Halaman Boomark

<p float="left">
<img src="screenshot/bookmark.png" alt="drawing" width="200"/>
<img src="screenshot/bookmark_detail.png" alt="drawing" width="200"/>
</p>

    Halaman QRCode

<p float="left">
<img src="screenshot/qrcode.png" alt="drawing" width="200"/>
<img src="screenshot/qrcode_success.png" alt="drawing" width="200"/>
<img src="screenshot/qrcode_failure.png" alt="drawing" width="200"/>
</p>

## Pembagian Kerja
- Juan Louis Rombetasik (13519075) : Daftar Faskes, Bookmark Faskes
- Nabila Hannania (13519097) : Landing Page (Splash Screen), Daftar Berita, Detail Berita
- Jordan Daniel Joshua (13519098) : Room, QRCode, Detail Faskes


