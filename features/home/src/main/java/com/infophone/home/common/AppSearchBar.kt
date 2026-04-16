package com.infophone.home.common

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.traversalIndex
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.infophone.homepage.R
import com.infophone.ui.theme.Gray600

@Composable
fun AppSearchBar() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 16.dp)
    ) {
        Row(
            modifier = Modifier
                .background(White, shape = RoundedCornerShape(70.dp))
                .border(1.dp, Color.LightGray, shape = RoundedCornerShape(70.dp))
                .fillMaxWidth()
                .heightIn(min = 42.dp)
                .padding(horizontal = 18.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(painter = painterResource(com.infophone.ui.R.drawable.ic_search_small), contentDescription = "Search", tint = Gray600)
            Spacer(modifier = Modifier.width(5.dp))
            BasicTextField(
                value = "",
                onValueChange = {},
                modifier = Modifier
                    .weight(1f)
                    .background(Color.Transparent),
                maxLines = 5,
                textStyle = TextStyle(
                    color = Color.DarkGray,
                    fontSize = 14.sp
                ),
                decorationBox = { innerTextField ->
                    Box(
                        modifier = Modifier.padding(8.dp)
                    ) {
                        if (/* value is empty */ true) {
                            Text(
                                text = "Search here...",
                                color = Color.Gray.copy(alpha = 0.6f),
                                fontSize = 14.sp
                            )
                        }
                        innerTextField()
                    }
                }
            )
            /*IconButton(
                modifier = Modifier
                    .width(32.dp),
                onClick = {  }
            ) {
                Icon(painter = painterResource(R.drawable.ic_plus), contentDescription = "Attachment", tint = Gray600)
            }*/
        }
    }
}

/*@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppSearchBar() {

    var searchQuery by remember { mutableStateOf("") }
    val items = listOf(
        "Apple", "Banana", "Cherry",
        "Date", "Elderberry", "Fig",
        "Grape", "Honeydew"
    )
    var active by remember { mutableStateOf(false) }
    val filteredItems = items.filter { it.contains(searchQuery, ignoreCase = true) }

    val colors1 = SearchBarDefaults.colors(
        containerColor = MaterialTheme.colorScheme.surfaceContainerLow,
    )

    SearchBar(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 0.dp)
            .fillMaxWidth(),
        inputField = {
            SearchBarDefaults.InputField(
                query = searchQuery,
                onQueryChange = { searchQuery = it },
                onSearch = { active = false },
                expanded = false,
                onExpandedChange = {  },
                placeholder = {
                    Text(
                        "Search",
//                        style = MaterialTheme.typography.bodyMedium,
                    ) },
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_search_small),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                },
                trailingIcon = {
                    if (active)
                        Icon(
                            imageVector = Icons.Rounded.Close,
                            contentDescription = null
                        )
                },
                colors = colors1.inputFieldColors,
            )
        },
        expanded = false,
        onExpandedChange = {  },
        shape = SearchBarDefaults.inputFieldShape,
        colors = colors1,
        tonalElevation = 0.dp,
        shadowElevation = SearchBarDefaults.ShadowElevation,
        windowInsets = WindowInsets.ime,
        content = {
            //Search content here
            filteredItems.forEach { item ->
                Text(text = item, fontSize = 16.sp)
            }
        },
    )
}*/


/*OutlinedTextField(
value = "",
onValueChange = {},
textStyle = MaterialTheme.typography.labelMedium,
placeholder = { Text("Search here...") },
leadingIcon = {
    Icon(
        painter = painterResource(id = R.drawable.ic_search_small),
        contentDescription = "Search"
    )
},
singleLine = true,
modifier = Modifier
.fillMaxWidth()
.height(48.dp)
.padding(0.dp),
shape = RoundedCornerShape(76.dp),*/
/*colors = OutlinedTextFieldDefaults.colors(
    focusedBorderColor = Color.Transparent,
    unfocusedBorderColor = Color.Transparent,
    disabledBorderColor = Color.Transparent,
    focusedContainerColor = Color.Transparent,
    unfocusedContainerColor = Color.Transparent,
))*/
