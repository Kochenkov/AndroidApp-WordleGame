package com.vkochenkov.wordlegame.presentation.screen.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.navigation.NavController
import com.vkochenkov.wordlegame.R
import com.vkochenkov.wordlegame.model.Language
import com.vkochenkov.wordlegame.presentation.theme.WordleTheme
import com.vkochenkov.wordlegame.presentation.theme.Yellow
import org.koin.androidx.compose.getViewModel

@Composable
fun SettingsScreen(
    navController: NavController,
) {
    val viewModel = getViewModel<SettingsViewModel>()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = WordleTheme.colors.background)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Card {
            Box(
                modifier = Modifier
                    .background(Yellow)
                    .padding(8.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    fontSize = 16.sp,
                    text = stringResource(R.string.settings_prevention),
                    color = WordleTheme.colors.content
                )
            }
        }

        Column(
            horizontalAlignment = Alignment.End,
            modifier = Modifier.padding(top = 16.dp)
        ) {

            val language = viewModel.screenState.value!!.language
            var langExpanded by remember { mutableStateOf(false) }
            var langFieldSize by remember { mutableStateOf(Size.Zero) }

            Row(
                modifier = Modifier
                    .clickable {
                        langExpanded = true
                    }
                    .onGloballyPositioned { coordinates ->
                        langFieldSize = coordinates.size.toSize()
                    },
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    fontSize = 18.sp,
                    text = "${stringResource(R.string.game_lang)} $language",
                    color = WordleTheme.colors.content
                )
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = null,
                    tint = WordleTheme.colors.content
                )
            }

            DropdownMenu(
                expanded = langExpanded,
                onDismissRequest = { langExpanded = false },
                modifier = Modifier
                    .width(with(LocalDensity.current) { langFieldSize.width.toDp() })
                    .background(WordleTheme.colors.background)
            ) {
                for (lang in Language.values()) {
                    DropdownMenuItem(
                        onClick = {
                            viewModel.onLanguageChanged(lang)
                            langExpanded = false
                        }) {
                        Text(text = lang.name, color = WordleTheme.colors.content)
                    }
                }
            }
        }
    }
}