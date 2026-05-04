# Catatan Tugas Praktikum - Android Storage
## 👩‍💻 Identitas Mahasiswa
* **Nama:** Baiq Alfia Zahira
* **NIM:** F1D02310042

## ✨ Fitur & Implementasi Penyimpanan
Aplikasi ini berhasil mengimplementasikan 3 Tujuan Pembelajaran utama sesuai modul:

1. **SharedPreferences (Sesi Login)**
   Digunakan untuk menyimpan status login pengguna secara sederhana (Key-Value). Jika pengguna sudah memasukkan nama dan masuk, aplikasi akan mengingat sesi tersebut. Saat aplikasi ditutup dan dibuka kembali, pengguna akan langsung diarahkan ke halaman utama tanpa harus login ulang.

2. **Room Database (CRUD Catatan)**
   Menggunakan abstraksi SQLite via *Room Database* untuk menyimpan daftar tugas (Judul & Deskripsi) secara persisten dan terstruktur. Data ditampilkan menggunakan *RecyclerView* dan *CardView*. Pemrosesan data menggunakan *Kotlin Coroutines* (LifecycleScope) agar UI tidak terblokir.

3. **App-Specific Storage (Backup File)**
   Menggunakan `openFileOutput` dengan `Context.MODE_PRIVATE` untuk mengekstrak data dari Room Database dan menyimpannya ke dalam file fisik bernama `BackupCatatan.txt` di dalam memori internal khusus aplikasi.

## 📱 Screenshot Aplikasi


### 1. Halaman Login (SharedPreferences)

<img src="https://github.com/user-attachments/assets/337f5c02-2803-41e2-b14b-bff82a38a7a2" width="250" alt="Login Screen" />

> Aplikasi meminta nama pengguna. Setelah login, data disimpan di SharedPreferences.

<img src="https://github.com/user-attachments/assets/337f5c02-2803-41e2-b14b-bff82a38a7a2" width="250" alt="berhasil login" />

> Aplikasi memunculkan pop up "Halo Alfia" saat masuk ke Main screen.

### 2. Halaman Utama & Input Data (Room Database)

<img src="https://github.com/user-attachments/assets/edb0747d-991d-41f5-9c5c-abb130596b8f" width="250" alt="input tugas" />

> Menambahkan tugas baru. Data langsung disimpan ke dalam Room Database dan muncul di daftar.

<img src="https://github.com/user-attachments/assets/95ef15ad-ae80-4daa-8ece-2eabd00d0be1" width="250" alt="hasil fix" />

> Tugas yang baru ditambahkan akan muncul di daftar yang paling atas.
 
### 3. Bukti Penyimpanan File (App-Specific Storage)

<img src="https://github.com/user-attachments/assets/2d07caa1-4b63-4b95-ac43-d648a7d68bcb" width="700" alt="Bukti File TXT" />

> Menekan tombol backup akan merangkum seluruh tugas menjadi satu teks utuh dan menyimpannya ke file `BackupCatatan.txt`.
---
