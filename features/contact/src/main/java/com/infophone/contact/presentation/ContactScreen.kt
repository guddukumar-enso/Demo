package com.infophone.contact.presentation

import CustomAppBar
import androidx.compose.runtime.Composable
import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Divider
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.core.content.PermissionChecker
import androidx.hilt.navigation.compose.hiltViewModel
import coil3.compose.AsyncImage
import com.infophone.contact.domain.model.Contact


import kotlinx.coroutines.launch

@Composable
fun ContactScreen(
    modifier: Modifier,
    isFeature: Boolean = false,
    onContactClick: (Contact) -> Unit,
    viewModel: ContactsViewModel = hiltViewModel(),
    selectUser: @Composable (Contact, List<Contact>) -> Unit = { _, _ -> },
    header: LazyListScope.() -> Unit = {},
) {
    val context = LocalContext.current

    var hasPermission by remember {
        mutableStateOf(
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.READ_CONTACTS
            ) == PermissionChecker.PERMISSION_GRANTED
        )
    }

    val permissionLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) { granted ->
            hasPermission = granted
        }

    LaunchedEffect(Unit) {
        if (!hasPermission) {
            permissionLauncher.launch(Manifest.permission.READ_CONTACTS)
        }
    }

    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(hasPermission) {
        if (hasPermission) {
            viewModel.loadContacts()
        }
    }

    var searchQuery by rememberSaveable { mutableStateOf("") }

    val filteredContacts by remember(uiState.contacts, searchQuery) {
        mutableStateOf(
            if (searchQuery.isBlank()) {
                uiState.contacts
            } else {
                val q = searchQuery.trim()
                uiState.contacts.filter { contact ->
                    contact.name.contains(q, ignoreCase = true) ||
                            (contact.phone?.contains(q) == true)
                }
            }
        )
    }

    when {
        !hasPermission -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) { Text("Contacts permission is required") }
        }
        uiState.isLoading -> {
            ShimmerContactListPlaceholder()
        }
        uiState.errorMessage != null -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) { Text("Error: ${uiState.errorMessage}") }
        }
        else -> {
            val frequently = filteredContacts.take(2)
            val remaining = filteredContacts.drop(frequently.size)

            val grouped: Map<Char, List<Contact>> = remember(remaining) {
                remaining.groupBy { contact ->
                    contact.name.firstOrNull()?.uppercaseChar() ?: '#'
                }.toSortedMap()
            }

            EnhancedContactsList(
                query = searchQuery,
                onQueryChange = { searchQuery = it },
                frequently = frequently,
                grouped = grouped,
                onClick = onContactClick,
                selectUser = selectUser,
                header = header,
                totalContacts = uiState.contacts
            )
        }
    }
}

// Shimmer

@Composable
fun ShimmerContactListPlaceholder(count: Int = 10) {
    val transition = rememberInfiniteTransition(label = "shimmer")
    val shimmerX by transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1200, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "x"
    )

    val brush = Brush.linearGradient(
        colors = listOf(
            Color.LightGray.copy(alpha = 0.6f),
            Color.LightGray.copy(alpha = 0.2f),
            Color.LightGray.copy(alpha = 0.6f)
        ),
        start = Offset(shimmerX - 200f, 0f),
        end = Offset(shimmerX, 0f)
    )

    LazyColumn(

        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF7F7F7))
            .padding(end = 24.dp)
    ) {
        items(count) {
            ShimmerContactRowPlaceholder(brush)
        }
    }
}

@Composable
fun ShimmerContactRowPlaceholder(brush: Brush) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .background(brush)
        )
        Spacer(modifier = Modifier.width(14.dp))
        Column(modifier = Modifier.fillMaxWidth()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.6f)
                    .height(18.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .background(brush)
            )
            Spacer(modifier = Modifier.height(6.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.4f)
                    .height(14.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .background(brush)
            )
        }
    }
}

// Enhanced UI

@OptIn(ExperimentalFoundationApi::class) // <-- Add this annotation
@Composable
fun EnhancedContactsList(
    query: String,
    onQueryChange: (String) -> Unit,
    frequently: List<Contact>,
    totalContacts: List<Contact>,
    grouped: Map<Char, List<Contact>>,
    onClick: (Contact) -> Unit,
    selectUser: @Composable (Contact, List<Contact>) -> Unit = { _, _ -> },
    header: LazyListScope.() -> Unit = {},


) {
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    val letterIndexMap: Map<Char, Int> = remember(frequently, grouped) {
        var index = 0
        index += 1 // search
        index += 3 // actions
        if (frequently.isNotEmpty()) index += 1

        buildMap {
            grouped.forEach { (letter, _) ->
                put(letter, index)
                index += 2 // header + card
            }
        }
    }

    Row(Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier
                .weight(1f),
            state = listState
        ) {


                header()



            ContactsGroupedSection(
                frequently = frequently,
                grouped = grouped,
                onClick = onClick,
                selectUser = selectUser,
                totalContacts = totalContacts
            )

        }

        AlphabetIndexBar(
            letters = grouped.keys.toList(),
            onLetterClick = { letter ->
                letterIndexMap[letter]?.let { target ->
                    coroutineScope.launch {
                        listState.scrollToItem(target)
                    }
                }
            },
            modifier = Modifier.align(Alignment.CenterVertically)
        )
    }



}


fun LazyListScope.ContactsGroupedSection(
    frequently: List<Contact>,
    totalContacts: List<Contact>,
    grouped: Map<Char, List<Contact>>,
    onClick: (Contact) -> Unit,
    selectUser: @Composable (Contact, List<Contact>) -> Unit = { _, _ -> },

) {

    // 🔹 Frequently Contacted
    if (frequently.isNotEmpty()) {
        item {
            FrequentContactsSection(
                title = "Frequently Contacted",
                contacts = frequently,
                onClick = onClick,
                selectUser = selectUser,
                totalContacts = totalContacts
            )
        }
    }

    // 🔹 Alphabetically Grouped
    grouped.forEach { (letter, list) ->
        stickyHeader {
            LetterHeader(letter)
        }
        item {
            LetterContactsCard(
                contacts = list,
                onClick = onClick,
                selectUser = selectUser,
                totalContacts = totalContacts
            )
        }
    }
}


@Composable
fun FrequentContactsSection(
    title: String,
    contacts: List<Contact>,
    totalContacts: List<Contact>,
    onClick: (Contact) -> Unit,
    selectUser: @Composable (Contact, List<Contact>) -> Unit = { _, _ -> },

) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = title,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(20.dp))
                .background(Color.White)
                .padding(vertical = 8.dp)
        ) {
            contacts.forEachIndexed { index, contact ->
                ContactCard(contact, totalContacts = totalContacts, onClick, selectUser = selectUser)
                if (index != contacts.size - 1) {
                    Divider(
                        modifier = Modifier.padding(horizontal = 16.dp),
                        color = Color(0xFFEAEAEA)
                    )
                }
            }
        }
    }
}

@Composable
fun LetterHeader(letter: Char) {
    Text(
        text = letter.toString(),
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, top = 12.dp, bottom = 6.dp)
    )
}

@Composable
fun LetterContactsCard(
    contacts: List<Contact>,
    totalContacts: List<Contact>,
    onClick: (Contact) -> Unit,
    selectUser: @Composable (Contact, List<Contact>) -> Unit = { _, _ -> },

    ) {
    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 4.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(Color.White)
    ) {
        contacts.forEachIndexed { index, contact ->
            ContactCard(contact, totalContacts = totalContacts, onClick, selectUser = selectUser)
            if (index != contacts.size - 1) {
                HorizontalDivider(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    thickness = DividerDefaults.Thickness, color = Color(0xFFEAEAEA)
                )
            }
        }
    }
}

@Composable
fun ContactCard(
    contact: Contact,
    totalContacts: List<Contact>,
    onClick: (Contact) -> Unit,
    selectUser: @Composable (Contact, List<Contact>) -> Unit = { _, _ -> },

) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick(contact) }
            .padding(horizontal = 10.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        ContactProfileImage(contact.name, contact.photoUri)
        Spacer(modifier = Modifier.width(14.dp))
        Column (
            modifier = Modifier.weight(1f)
        ){
            Text(
                text = contact.name,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = contact.phone ?: "",
                fontSize = 14.sp,
                color = Color.Gray
            )
        }
        selectUser(contact, totalContacts)

    }
}

@Composable
fun ContactProfileImage(name: String, photoUri: String?) {
    if (photoUri != null) {
        AsyncImage(
            model = photoUri,
            contentDescription = null,
            modifier = Modifier
                .size(52.dp)
                .clip(CircleShape)
        )
    } else {
        Box(
            modifier = Modifier
                .size(52.dp)
                .clip(CircleShape)
                .background(Color(0xFFBBDEFB)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = name.take(1).uppercase(),
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Color(0xFF0D47A1)
            )
        }
    }
}

@Composable
fun AlphabetIndexBar(
    letters: List<Char>,
    onLetterClick: (Char) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxHeight()
            .width(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        letters.forEach { letter ->
            Text(
                text = letter.toString(),
                fontSize = 10.sp,
                modifier = Modifier
                    .padding(vertical = 2.dp)
                    .clickable { onLetterClick(letter) },
                color = Color.Gray
            )
        }
    }
}

