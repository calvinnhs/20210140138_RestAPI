package com.example.databaseapi.ui

import android.text.Editable.Factory
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.consumeapi.ui.home.viewmodel.HomeViewModel
import com.example.consumeapi.ui.kontak.viewmodel.InsertViewModel
import com.example.databaseapi.KontakAplikasi
import com.example.databaseapi.ui.kontak.viewModel.DetailsViewModel
import com.example.databaseapi.ui.kontak.viewModel.EditViewModel

object PenyediaViewModel {
    val Factory = viewModelFactory {
        initializer { HomeViewModel(aplikasiKontak().container.kontakRepository) }
        initializer { InsertViewModel(aplikasiKontak().container.kontakRepository) }
        initializer {
            DetailsViewModel(
                createSavedStateHandle(),
                kontakRepository = aplikasiKontak().container.kontakRepository
            )
        }
        initializer {
            EditViewModel(
                createSavedStateHandle(),
                kontakRepository = aplikasiKontak().container.kontakRepository
            )
        }
    }
}



fun CreationExtras.aplikasiKontak() : KontakAplikasi =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as KontakAplikasi)