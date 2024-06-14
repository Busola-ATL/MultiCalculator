import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

import multicalculator.composeapp.generated.resources.Res
import multicalculator.composeapp.generated.resources.compose_multiplatform

@Composable
@Preview
fun App() {
    MaterialTheme {
        var showContent by remember { mutableStateOf(false) }
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Button(onClick = { showContent = !showContent }) {
                Text("Click me!")
            }
            AnimatedVisibility(showContent) {
                val greeting = remember { Greeting().greet() }
                Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(painterResource(Res.drawable.compose_multiplatform), null)
                    Text("Compose: $greeting")
                }
            }
        }
    }
}

@Composable
fun CalcView(){

}

@Composable
fun CalcRow(){

}
@Composable
fun CalcDisplay(){

}
@Composable
fun CalcNumericButton(number: Int, display: MutableState<String>, modifier: Modifier = Modifier){
    Button(
        onClick = {
            // Append the number to the display value
            if (display.value == "0") {
                display.value = number.toString()
            } else {
                display.value += number.toString()
            }
        },
        modifier = modifier
            .padding(4.dp)
    ) {
        Text(text = number.toString())
    }
}
@Composable
fun CalcOperationButton(operation: String, display: MutableState<String>, modifier: Modifier = Modifier){
    Button(
        onClick = { "" },
        modifier = modifier
            .padding(4.dp)
    ) {
        Text(text = operation)
    }
}
@Composable
fun CalcEqualsButton(display: MutableState<String>){
    Button(
        onClick = { display.value = "0" },
        modifier = Modifier
            .padding(4.dp)
    ) {
        Text(text = "=")
    }
}