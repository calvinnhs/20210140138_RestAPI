package com.example.databaseapi.ui.home.screen

import com.example.databaseapi.model.Kontak
import com.example.databaseapi.ui.home.viewmodel.KontakUIState

fun HomeStatus(
    kontakUIState: KontakUIState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier,
    onDeleteClick: (Kontak) -> Unit = {},
    onDetailClick: (Int) -> Unit
) {
    when (kontakUIState){
        is KontakUIState.Loading -> OnLoading(modifier = modifier.fillMaxSize())
        is KontakUIState.Success -> KontakLayout(
            kontak = kontakUIState.kontak, modifier = modifier.fillMaxWidth(),
            onDetailClick = {
                onDetailClick(it.id)
            },
            onDeleteClick = {
                onDeleteClick(it)
            }
        )

        is KontakUIState.Error -> OnError(retryAction, modifier = modifier.fillMaxSize())
    }
}