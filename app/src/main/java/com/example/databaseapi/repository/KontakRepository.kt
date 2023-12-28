package com.example.databaseapi.repository

import com.example.databaseapi.model.Kontak
import com.example.databaseapi.service_api.KontakService
import java.io.IOException

interface KontakRepository {
    suspend fun getKontak(): List<Kontak>

    suspend fun insertKontak(kontak: Kontak)

    suspend fun updateKontak(id : Int, kontak: Kontak)

    suspend fun deleteKontak(id: Int)

    suspend fun getKontakById(id: Int): Kontak
}

class NetworkKontakRepository(
    private val kontakAplikasiService : KontakService
) : KontakRepository {
    override suspend fun getKontak(): List<Kontak> = kontakAplikasiService.getKontak()

    override suspend fun insertKontak(kontak: Kontak) = kontakAplikasiService.insertKontak(kontak)

    override suspend fun updateKontak(id: Int, kontak: Kontak) = kontakAplikasiService.updateKontak(id, kontak)

    override suspend fun deleteKontak(id: Int) {
        try{
            val response = kontakAplikasiService.deleteKontak(id)
            if(!response.isSuccessful){
                throw IOException("Failed to delete kontak, HTTP status Code : "+" ${response.code()}")
            }
            else{
                response.message()
                println(response.message())
            }
        }
        catch (e:Exception){
            throw e
        }
    }
    override suspend fun getKontakById(id: Int): Kontak {
        return kontakAplikasiService.getKontakById(id)
    }
}