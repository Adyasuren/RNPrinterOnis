# onis-printer

bluetooth and native printer

## Installation

```sh
npm install onis-printer or yarn add onis-printer
```

## Usage

```js
import {
  initPrinter,
  printText,
  connectBTPrinter,
  initBtPrinter,
  printNewLine,
  setBold,
  setAlign,
  setSmall,
  printLine,
  setLineSpacing,
  feedPaper,
  getPairedDevice,
} from 'onis-printer';

const result = await initPrinter();

await printText('text');

const result = await connectBTPrinter();

await initBtPrinter();

await printNewLine();

await setBold(true / false);

// 100 - center, 101 - right, 102 - left
await setAlign(100);

await setSmall(true / false);

await printLine();

await setLineSpacing(30);

await feedPaper();

const result = await getPairedDevice();
```

## Contributing

See the [contributing guide](CONTRIBUTING.md) to learn how to contribute to the repository and the development workflow.

## License

MIT

---

Made with [Adyasuren](https://github.com/Adyasuren)
