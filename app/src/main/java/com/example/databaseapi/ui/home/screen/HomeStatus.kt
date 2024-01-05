package com.example.consumeapi.ui.home.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.consumeapi.ui.home.viewmodel.KontakUIState
import com.example.databaseapi.R
import com.example.databaseapi.model.Kontak

@Composable
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

@Composable
fun OnLoading(modifier: Modifier = Modifier){
    Image(
        modifier = modifier.size(200.dp),
        painter = painterResource(R.drawable.loading_img),
        contentDescription = stringResource(R.string.loading)
    )
}

@Composable
fun OnError(retryAction: () -> Unit, modifier: Modifier = Modifier){
    Column (
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Image(
            painter = painterResource(id = R.drawable.ic_connection_error),
            contentDescription = ""
        )
        Text(
            text = stringResource(id = R.string.loading_failed), modifier = Modifier.padding(16.dp))
        Button(onClick = retryAction) {
            Text(stringResource(R.string.retry))

        }
    }
}

@Composable
fun KontakLayout(
    kontak: List<Kontak>,
    modifier: Modifier = Modifier,
    onDeleteClick: (Kontak) -> Unit,
    onDetailClick: (Kontak) -> Unit = {}
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(kontak) { kontak ->
            KontakCard(kontak = kontak,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onDetailClick(kontak) },
                onDeleteClick = {onDeleteClick(kontak)
                }
            )
        }
    }

}
@Composable
fun KontakCard(
    kontak : Kontak,
    onDeleteClick: (Kontak) -> Unit = {},
    modifier: Modifier = Modifier
){
    Card (
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ){
        Column (
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ){
            Row (
                modifier = Modifier.fillMaxWidth(),
            ){
                Text(
                    text = kontak.name,
                    style = MaterialTheme.typography.titleLarge,
                )
                Spacer(Modifier.weight(1f))
                IconButton(onClick = {onDeleteClick(kontak)}){
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = null)
                }
                Text(
                    text = kontak.telpon,
                    style = MaterialTheme.typography.titleMedium
                )
            }
            Text(
                text = kontak.email,
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}