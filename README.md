# Memory Game CLI

Memory Game CLI adalah permainan asah ingatan sederhana yang berjalan di Command Line Interface (CLI) menggunakan Java. 
Pemain ditantang untuk mengingat urutan angka acak yang hanya muncul selama beberapa detik sebelum hilang.

## Cara Menjalankan

Pastikan Java telah terinstal di komputer Anda.

1. Buat folder bin untuk menampung hasil kompilasi:
   mkdir bin

2. Kompilasi seluruh kode dari folder src ke folder bin:
   javac -d bin src/*.java

3. Jalankan permainan:
   java -cp bin Game

## Alur Permainan (Game Flow)

1. Menu Utama
   Pemain disajikan tiga tingkat kesulitan:
   - Mudah: 4 angka (waktu hafal 3 detik)
   - Sedang: 6 angka (waktu hafal 4 detik)
   - Sulit: 10 angka (waktu hafal 5 detik)

2. Fase Menghafal
   Program menampilkan deretan angka acak dari 0–99.
   Hitung mundur tampil sesuai durasi tingkat kesulitan.

3. Fase Menebak
   Setelah waktu habis, layar dibersihkan.
   Pemain mengetik ulang angka-angka tersebut (dipisahkan spasi).
   Contoh: 23 55 12 9

4. Hasil
   Program membandingkan jawaban pemain dan jawaban asli.
   - Jika benar → tampil pesan sukses berwarna hijau.
   - Jika salah → tampil pesan merah + kunci jawaban.

5. Pengulangan
   Setelah ronde selesai, pemain kembali ke menu utama 
   untuk mencoba lagi atau keluar dari permainan.

## Alur Kode (Code Flow)

1. Game.java (Entry Point)
   - Menjalankan method main.
   - Membuat objek UI, Utils, dan MemoryEngine.
   - Menjalankan loop utama hingga pemain memilih keluar.

2. UI.java dan Utils.java (Tampilan & Input)
   - ui.cetakMenu() untuk menampilkan menu.
   - utils.bacaBaris() membaca input pemain.
   - Utils juga melakukan validasi input.
   - ui.bersihkanLayar() membersihkan tampilan terminal.

3. MemoryEngine.java (Logika Inti)
   - mulaiRonde(tingkat) dipanggil setelah pemain memilih level.
   - buatUrutan() menghasilkan deretan angka acak.
   - tampilkanUrutan() menampilkan angka dengan hitung mundur.
   - evaluasi() membandingkan jawaban pemain.

4. Penyelesaian Permainan
   Hasil evaluasi ditampilkan melalui UI.
   Setelah itu alur kembali ke Game.java untuk menunggu input pemain berikutnya.
