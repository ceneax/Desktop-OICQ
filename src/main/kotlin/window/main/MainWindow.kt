package window.main

import OICQ
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import base.baseWindow

fun mainWindow() = baseWindow(
    title = "OICQ",
    size = IntSize(450, 900)
) {
    Column(modifier = Modifier.padding(10.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(imageVector = Icons.Default.AccountCircle)
            Column {
                Text(text = OICQ.nick)
                Text(text = OICQ.id.toString())
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Column(modifier = Modifier.clip(shape = RoundedCornerShape(10.dp))) {
            OICQ.friends.forEach {
                Row(
                    modifier = Modifier.background(color = Color.Gray)
                        .fillMaxWidth()
                        .padding(10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(imageVector = Icons.Default.Person)

                    Spacer(modifier = Modifier.width(10.dp))

                    Column {
                        Text(text = it.nick)
                        Text(text = it.id.toString())
                    }
                }

                Spacer(modifier = Modifier.height(5.dp))
            }

            OICQ.groups.forEach {
                Row(
                    modifier = Modifier.background(color = Color.Cyan)
                        .fillMaxWidth()
                        .padding(10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(imageVector = Icons.Default.Person)

                    Spacer(modifier = Modifier.width(10.dp))

                    Column {
                        Text(text = it.name)
                        Text(text = it.id.toString())
                    }
                }

                Spacer(modifier = Modifier.height(5.dp))
            }

            OICQ.strangers.forEach {
                Row(
                    modifier = Modifier.background(color = Color.Yellow)
                        .fillMaxWidth()
                        .padding(10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(imageVector = Icons.Default.Person)

                    Spacer(modifier = Modifier.width(10.dp))

                    Column {
                        Text(text = it.nick)
                        Text(text = it.id.toString())
                    }
                }

                Spacer(modifier = Modifier.height(5.dp))
            }
        }
    }
}