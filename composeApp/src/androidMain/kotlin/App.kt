import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
            CalcView()
        }
    }
}

    @Composable
    fun CalcView() {
        val displayText = remember { mutableStateOf("0") }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.LightGray)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // First Row: Display
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                CalcDisplay(displayText.value)
            }

            // Second Row: Operators
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Column {
                    CalcOperationButton("/", displayText)
                    CalcOperationButton("*", displayText)
                }
                Column {
                    CalcOperationButton("-", displayText)
                    CalcOperationButton("+", displayText)
                }
            }

            // Columns with numeric buttons
            for (i in 7 downTo 1 step 3) {
                Column {
                    CalcRow(displayText, i, 3) {
                        // No additional buttons needed here for now
                    }
                }
            }

            // Last Row: Numeric Buttons and Equals Button
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                CalcNumericButton(0, displayText)
                CalcEqualsButton(displayText)
            }
        }
    }

    @Composable
    fun CalcRow(
        display: MutableState<String>,
        startNum: Int,
        numButtons: Int,
        content: @Composable RowScope.() -> Unit
    ) {
        val endNum = startNum + numButtons
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            for (num in startNum until endNum) {
                CalcNumericButton(number = num, display = display, modifier = Modifier.weight(1f))
            }
            content()
        }
    }

    @Composable
    fun CalcDisplay(display: String, modifier: Modifier = Modifier) {
        Text(
            text = display,
            modifier = modifier
                .height(50.dp)
                .padding(5.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.End,
            style = MaterialTheme.typography.h4.copy(fontWeight = FontWeight.Bold)
        )
    }

    @Composable
    fun CalcNumericButton(
        number: Int,
        display: MutableState<String>,
        modifier: Modifier = Modifier
    ) {
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
    fun CalcOperationButton(
        operation: String,
        display: MutableState<String>,
        modifier: Modifier = Modifier
    ) {
        Button(
            onClick = { display.value += operation },
            modifier = modifier
                .padding(4.dp)
        ) {
            Text(text = operation)
        }
    }

    @Composable
    fun CalcEqualsButton(display: MutableState<String>) {
        Button(
            onClick = { display.value = "0" },
            modifier = Modifier
                .padding(4.dp)
        ) {
            Text(text = "=")
        }
    }
