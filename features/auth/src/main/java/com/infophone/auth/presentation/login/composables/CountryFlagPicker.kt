
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.infophone.auth.R
import com.infophone.auth.domain.model.CountryItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CountryCodePicker() {

    // --- Sample list ---
    val countryList = listOf(
        CountryItem("Afghanistan", "+93", R.drawable.ic_ind_flag),
        CountryItem("Albania", "+355", R.drawable.ic_ind_flag),
        CountryItem("Algeria", "+213", R.drawable.ic_ind_flag),
        CountryItem("American Samoa", "+1-684", R.drawable.ic_ind_flag),
        CountryItem("Andorra", "+376", R.drawable.ic_ind_flag),
        CountryItem("Angola", "+244", R.drawable.ic_ind_flag),

    )


    var expanded by remember { mutableStateOf(false) }
    var selectedCountry by remember { mutableStateOf(countryList[0]) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
//       modifier = Modifier.fillMaxWidth()
    ) {



        Row(
            modifier = Modifier.
                 width(width = 100.dp)
                .width(width = 100.dp)
                .padding(start = 8.dp)
                .clickable { expanded = !expanded },
            verticalAlignment = Alignment.CenterVertically
        ) {

            // Flag
            androidx.compose.foundation.Image(
                painter = painterResource(id = selectedCountry.flag),
                contentDescription = "Flag",
                modifier = Modifier.size(32.dp)
            )

            Spacer(modifier = Modifier.width(8.dp))
            Icon(imageVector = Icons.Filled.KeyboardArrowDown, contentDescription = null)
            Spacer(modifier = Modifier.width(8.dp))

            // Arrow Icon
            Box(
                modifier = Modifier
                    .width(2.dp)
                    .height(30.dp)
                    .background(Color.Black)
            )
        }

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.width(width = 250.dp)
        ) {

            countryList.forEach { country ->
                DropdownMenuItem(
                    text = {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            androidx.compose.foundation.Image(
                                painter = painterResource(id = country.flag),
                                contentDescription = null,
                                modifier = Modifier.size(28.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("${country.name} (${country.code})")
                        }
                    },
                    onClick = {
                        selectedCountry = country
                        expanded = false
                    },
                )
            }
        }
    }
}

@Preview
@Composable
fun CountryCodePickerPreview() {
    CountryCodePicker()
}
