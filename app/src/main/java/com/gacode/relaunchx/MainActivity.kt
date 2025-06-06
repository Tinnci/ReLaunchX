package com.gacode.relaunchx

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.gacode.relaunchx.ui.theme.ReLaunchXModernTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.remember
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Icon
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.ui.Alignment
import androidx.compose.foundation.layout.height
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.layout.size

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ReLaunchXModernTheme {
                MainScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    Scaffold(
        topBar = { TopBar() },
        bottomBar = { BottomBar() },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        FileList(modifier = Modifier.padding(innerPadding))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar() {
    CenterAlignedTopAppBar(
        title = { Text("当前目录", fontSize = 20.sp) }, // Placeholder for current directory
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary,
            actionIconContentColor = MaterialTheme.colorScheme.onPrimary,
            navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
        ),
        navigationIcon = {
            IconButton(onClick = { /* TODO: Handle navigation icon click */ }) {
                Icon(Icons.Filled.Menu, contentDescription = "Menu")
            }
        },
        actions = {
            IconButton(onClick = { /* TODO: Handle search click */ }) {
                Icon(Icons.Filled.Search, contentDescription = "Search")
            }
        }
    )
}

@Composable
fun BottomBar() {
    BottomAppBar(
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = MaterialTheme.colorScheme.onPrimary,
        modifier = Modifier.height(56.dp) // Standard BottomAppBar height
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Placeholder for memory info
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("内存")
                Text("100M")
            }
            // Placeholder for battery info
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("电量")
                Text("80%")
            }
            // Placeholder for WiFi status
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("WiFi")
                Text("ON")
            }
        }
    }
}

@Composable
fun FileList(modifier: Modifier = Modifier) {
    val dummyItems = remember { // Replace with actual data from ViewModel later
        listOf(
            FileDetails("Document.pdf", "Document.pdf", "pdf", "/sdcard", "/sdcard/Document.pdf", FsItemType.File, java.util.Date(), 1024L, "pdfReader"),
            FileDetails("MyFolder", "MyFolder", "", "/sdcard", "/sdcard/MyFolder", FsItemType.Directory, java.util.Date(), 0L, ""),
            FileDetails("Image.jpg", "Image.jpg", "jpg", "/sdcard", "/sdcard/Image.jpg", FsItemType.File, java.util.Date(), 2048L, "imageViewer"),
            FileDetails("AnotherDoc.epub", "AnotherDoc.epub", "epub", "/sdcard", "/sdcard/AnotherDoc.epub", FsItemType.File, java.util.Date(), 1500L, "epubReader"),
            FileDetails("Music", "Music", "", "/sdcard", "/sdcard/Music", FsItemType.Directory, java.util.Date(), 0L, ""),
            FileDetails("Video.mp4", "Video.mp4", "mp4", "/sdcard", "/sdcard/Video.mp4", FsItemType.File, java.util.Date(), 5000L, "videoPlayer"),
            FileDetails("Presentation.pptx", "Presentation.pptx", "pptx", "/sdcard", "/sdcard/Presentation.pptx", FsItemType.File, java.util.Date(), 3000L, "officeViewer"),
            FileDetails("Downloads", "Downloads", "", "/sdcard", "/sdcard/Downloads", FsItemType.Directory, java.util.Date(), 0L, ""),
            FileDetails("Notes.txt", "Notes.txt", "txt", "/sdcard", "/sdcard/Notes.txt", FsItemType.File, java.util.Date(), 500L, "textEditor"),
            FileDetails("OldPhotos", "OldPhotos", "", "/sdcard", "/sdcard/OldPhotos", FsItemType.Directory, java.util.Date(), 0L, "")
        )
    }

    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 100.dp), // 响应式布局的关键！
        modifier = modifier.fillMaxSize().padding(horizontal = 8.dp, vertical = 8.dp)
    ) {
        items(dummyItems.size) { index ->
            FileListItem(item = dummyItems[index])
        }
    }
}

@Composable
fun FileListItem(item: FileDetails) {
    Column(
        modifier = Modifier.padding(4.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Placeholder Icon - replace with actual icons based on item.type or item.extension
        val icon: ImageVector = when (item.type) {
            FsItemType.Directory -> Icons.Filled.Menu // Use a folder icon for directories
            else -> Icons.Filled.Menu // Use a generic file icon for files
        }
        Icon(icon, contentDescription = item.name, modifier = Modifier.size(48.dp))

        Text(text = item.displayName, style = MaterialTheme.typography.bodySmall, maxLines = 2)
        if (item.type == FsItemType.File) {
            Text(text = "${item.size / 1024} KB", style = MaterialTheme.typography.bodySmall)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    ReLaunchXModernTheme {
        MainScreen()
    }
}