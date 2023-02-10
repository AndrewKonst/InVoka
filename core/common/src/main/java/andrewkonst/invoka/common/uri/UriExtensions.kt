package andrewkonst.invoka.common.uri

import android.content.ContentResolver
import android.content.Context
import android.net.Uri
import android.webkit.MimeTypeMap
import androidx.documentfile.provider.DocumentFile


fun Uri.mimeType(context: Context): String? {
    val extension = extension(context)
    return MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension)
}

fun Uri.name(context: Context): String? {
    return DocumentFile.fromSingleUri(context, this)?.name
}

fun Uri.extension(context: Context): String {
    val ext = if (this.scheme == ContentResolver.SCHEME_CONTENT) {
        MimeTypeMap.getSingleton()
            .getExtensionFromMimeType(context.contentResolver.getType(this))
    } else {
        MimeTypeMap.getFileExtensionFromUrl(this.toString())
    }
    return ext ?: error("Unknown uri extension")
}


