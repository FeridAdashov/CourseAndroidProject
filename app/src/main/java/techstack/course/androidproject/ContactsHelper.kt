package techstack.course.androidproject

import android.annotation.SuppressLint
import android.content.Context
import android.database.Cursor
import android.os.Bundle
import android.provider.ContactsContract
import androidx.loader.app.LoaderManager
import androidx.loader.content.CursorLoader
import androidx.loader.content.Loader

val PROJECTION: Array<out String> = arrayOf(
    ContactsContract.Contacts._ID,
    ContactsContract.Contacts.LOOKUP_KEY,
    ContactsContract.Contacts.DISPLAY_NAME_PRIMARY,
    ContactsContract.CommonDataKinds.Phone.NUMBER,
    ContactsContract.CommonDataKinds.Phone.HAS_PHONE_NUMBER,
    ContactsContract.Data.MIMETYPE,
)


class ContactsLoader(
    private val context: Context,
    private val onLoadContacts: (ArrayList<DataModel>) -> Unit
) :
    LoaderManager.LoaderCallbacks<Cursor> {

    override fun onCreateLoader(id: Int, args: Bundle?): Loader<Cursor> {
        return CursorLoader(
            context,
            ContactsContract.Data.CONTENT_URI,
            PROJECTION,
            null,
            null,
            null
        )
    }

    override fun onLoaderReset(loader: Loader<Cursor>) {

    }

    @SuppressLint("Range")
    override fun onLoadFinished(loader: Loader<Cursor>, cursor: Cursor?) {
        val list = arrayListOf<DataModel>()

        cursor?.let { cur ->
            if (cur.count > 0) {
                while (cur.moveToNext()) {
                    val name: String = cur.getString(
                        cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)
                    )
                    if (cur.getInt(
                            cur.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)
                        ) > 0
                    ) {
                        val phoneNo = cur.getString(
                            cur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)
                        )
                        val type = cur.getString(
                            cur.getColumnIndex(ContactsContract.Data.MIMETYPE)
                        )

                        if (type == ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
                            list.add(DataModel(name, phoneNo))
                    }
                }
            }

            onLoadContacts.invoke(list)
        }
    }
}