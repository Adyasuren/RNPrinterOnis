import { NativeModules, Platform } from 'react-native';

const LINKING_ERROR =
  `The package 'onis-printer' doesn't seem to be linked. Make sure: \n\n` +
  Platform.select({ ios: "- You have run 'pod install'\n", default: '' }) +
  '- You rebuilt the app after installing the package\n' +
  '- You are not using Expo Go\n';

const OnisPrinter = NativeModules.OnisPrinter
  ? NativeModules.OnisPrinter
  : new Proxy(
      {},
      {
        get() {
          throw new Error(LINKING_ERROR);
        },
      }
    );

// prettier-ignore
export async function multiply(a: number, b: number): Promise<number> {
  return await OnisPrinter.multiply(a, b);
}

// prettier-ignore
export async function initPrinter(): Promise<boolean> {
  return await OnisPrinter.initPrinter();
}

// prettier-ignore
export async function printText(string: String) {
  await OnisPrinter.printText(string);
}

// prettier-ignore
export async function connectBTPrinter(address: String): Promise<boolean> {
  return await OnisPrinter.connectBtPrinter(address);
}

// prettier-ignore
export async function initBtPrinter() {
  await OnisPrinter.initBtPrinter();
}

// prettier-ignore
export async function printNewLine() {
  await OnisPrinter.printNewLine();
}

// prettier-ignore
export async function setBold(isBold: boolean) {
  await OnisPrinter.setBold(isBold);
}

// prettier-ignore
export async function setAlign(align: number) {
  await OnisPrinter.setAlign(align);
}

// prettier-ignore
export async function setSmall(isSmall: boolean) {
  await OnisPrinter.setSmall(isSmall);
}

// prettier-ignore
export async function printLine() {
  await OnisPrinter.printLine();
}

// prettier-ignore
export async function setLineSpacing(size: Number) {
  await OnisPrinter.setLineSpacing(size);
}

// prettier-ignore
export async function feedPaper() {
  await OnisPrinter.feedPaper();
}

// prettier-ignore
export async function getPairedDevice(): Promise<any> {
  return await OnisPrinter.getPairedDevice();
}

// prettier-ignore
export async function isConnectPrinter(): Promise<boolean> {
  return await OnisPrinter.isConnectPrinter();
}

// prettier-ignore
export async function isBluetoothEnabled(): Promise<boolean> {
  return await OnisPrinter.isBluetoothEnabled();
}
