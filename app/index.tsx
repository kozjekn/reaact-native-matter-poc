import { Text, View } from "react-native";
import * as Matter from "../modules/matter-api";

export default function Index() {
  return (
    <Matter.MatterView style={{ flex: 1}} />
  );
}
