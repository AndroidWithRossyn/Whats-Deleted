package com.tiriig.soocelifariimaha.data.repository

import androidx.lifecycle.LiveData
import com.tiriig.soocelifariimaha.data.Database
import com.tiriig.soocelifariimaha.data.model.Chat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MessageRepository @Inject constructor(
    private val database: Database
)  {

    suspend fun saveMessage(chat: Chat) {
        withContext(Dispatchers.IO) {
            //fetch last message and make comparison to avoid duplicates
            val lastMessage = database.userDao().getLastMessage(chat.user)?:""
            if (chat.message != lastMessage) database.userDao().save(chat)
        }
    }

    suspend fun fetchMessages(): LiveData<List<Chat>> {
        return withContext(Dispatchers.IO){
            database.userDao().getMessages()
        }
    }

    suspend fun fetchMessagesByUser(user: String):LiveData<List<Chat>>{
        return withContext(Dispatchers.IO){
            database.userDao().getMessagesByUser(user)
        }
    }
}