package com.infophone.chat.util

import android.content.Context
import android.content.Intent
import android.net.Uri

fun shareMixedContent(context: Context, uris: List<Uri>, text: String) {
    val shareIntent = Intent().apply {
        // Use ACTION_SEND_MULTIPLE for 1 or more files
        action = Intent.ACTION_SEND_MULTIPLE

        // Attach the list of file URIs
        val uriArrayList = ArrayList<Uri>().apply { addAll(uris) }
        putParcelableArrayListExtra(Intent.EXTRA_STREAM, uriArrayList)

        // Attach the text content
        putExtra(Intent.EXTRA_TEXT, text)

        // Use wildcard MIME type for mixed content
        type = "*/*"

        // Important: Grant read permissions for the URIs
        addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
    }

    context.startActivity(Intent.createChooser(shareIntent, "Share to..."))
}

fun shareText(context: Context, text: String) {
    if (text.isEmpty()) return

    val sendIntent: Intent = Intent().apply {
        // Specify the action: Sending data
        action = Intent.ACTION_SEND

        // Put the actual text into the Intent
        putExtra(Intent.EXTRA_TEXT, text)

        // Set the MIME type so Android knows what kind of data this is
        type = "text/plain"
    }

    // Wrap it in a Chooser so the user always sees the selection sheet
    val shareIntent = Intent.createChooser(sendIntent, "Share message")

    context.startActivity(shareIntent)
}

