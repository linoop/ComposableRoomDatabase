package com.example.roomcomposable

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import com.example.roomcomposable.models.Customer
import com.example.roomcomposable.ui.theme.RoomComposableTheme
import com.example.roomcomposable.viewModels.CustomerViewModel
import java.util.*
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RoomComposableTheme {
                // A surface container using the 'background' color from the theme
                val viewModel = ViewModelProvider(this).get(CustomerViewModel::class.java)
                Surface(color = MaterialTheme.colors.background) {
                    CustomerListView(viewModel = viewModel)
                }
            }
        }
    }
}

@Composable
fun CustomerListView(viewModel: CustomerViewModel) {
    val customerList = viewModel.fetchAllCustomer().observeAsState()
    Scaffold(modifier = Modifier.fillMaxSize(), floatingActionButton = {
        ExtendedFloatingActionButton(backgroundColor = Color.Red, text = {
            Text(text = "Add", color = Color.White)
        }, onClick = {
            val name = UUID.randomUUID().toString()
            viewModel.insertCustomer(
                Customer(
                    name = name,
                    gender = "Male",
                    email = "abc@gmail.com"
                )
            )
        }, icon = {
            Icon(imageVector = Icons.Default.Add, contentDescription = "Add", tint = Color.White)
        })
    }) {
        LazyColumn {
            items(customerList.value ?: arrayListOf()) { item ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    val color = Color(Random.nextInt(256), Random.nextInt(256), Random.nextInt(256))
                    Box(
                        modifier = Modifier
                            .size(80.dp)
                            .border(width = 1.2.dp, color = color, shape = CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "A", color = color)
                    }
                    Spacer(modifier = Modifier.size(16.dp))
                    Column(modifier = Modifier.weight(2f)) {
                        Spacer(modifier = Modifier.size(8.dp))
                        Text(
                            text = item.name?.uppercase() ?: "",
                            color = color,
                            fontSize = 16.sp,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        Text(
                            text = "${item.gender}",
                            color = Color.Black,
                            fontSize = 14.6.sp
                        )
                        Text(
                            text = "${item.email}",
                            color = Color.Gray,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                    Spacer(modifier = Modifier.size(8.dp))
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete",
                        tint = Color.Red,
                        modifier = Modifier
                            .size(30.dp)
                            .clickable { viewModel.deleteCustomerById(item.id) })
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    RoomComposableTheme {
    }
}